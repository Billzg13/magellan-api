package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.PlacePackage.Location;
import com.example.ptuxiakh.model.PlacePackage.Place;
import com.example.ptuxiakh.model.SearchResult;
import com.example.ptuxiakh.model.SearchType;
import com.example.ptuxiakh.model.SolidSearch.QuickSearch;
import com.example.ptuxiakh.model.SolidSearch.QuickSearchHistoryV2;
import com.example.ptuxiakh.model.SolidSearch.QuickSearchResponse;
import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.repository.*;
import com.sun.xml.bind.v2.model.annotation.Quick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    QuickSearchRepository quickSearchRepository;

    @Autowired
    AdvancedSearchRepository advancedSearchRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuickSearchHistoryRepositoryV2 quickSearchHistoryRepositoryV2;

    @Autowired
    PlaceRepositoryV2 placeRepositoryV2;

    @Autowired
    UserHistoryRepository userHistoryRepository;

    @Value("${pythonService.url}")
    private String pythonBaseUrl;

    @Value("${pythonService.localUrl}")
    private String pythonLocalUrl;

    /**
     * This is the quick search, the user asks for a quick search of a place
     *
     * @param userId
     * @return
     */
    @Override
    public QuickSearchResponse quickSearch(String userId, SearchType type) throws Exception {
        User user = getUser(userId);
        QuickSearch quickSearch = new QuickSearch(user, type.getType());
        System.out.println("in quickSearch");
        System.out.println(type);
        QuickSearchResponse response = quickSearch.recommend(pythonBaseUrl);
        if (response ==  null){
            System.out.println(response);
            //if response is null something went wrong that we dont know off
            throw new Exception("something went wrong with python server");
        }
        return response;
    }

    @Override
    public QuickSearchHistoryV2 findQuickSearchById(String searchId) {
        QuickSearchHistoryV2 search = quickSearchHistoryRepositoryV2.findById(searchId).orElseThrow(() -> new RuntimeException("cant find"));
        List<SearchResult> sortedResults = search.getQuickSearchResponse().getResult()
                .stream().sorted(Comparator.comparingDouble(SearchResult::getCorrelation).reversed())
                    .collect(Collectors.toList());
        ArrayList<SearchResult> temp = new ArrayList<>();
        QuickSearchResponse quickSearchResponse = new QuickSearchResponse();
        if (sortedResults.size() > 30 ){
            temp.addAll(sortedResults.subList(0, 29));
            quickSearchResponse = new QuickSearchResponse(temp);
        }else{
            temp.addAll(sortedResults);
            quickSearchResponse = new QuickSearchResponse(temp);
        }
        quickSearchResponse.setCount(temp.size());
        search.setQuickSearchResponse(quickSearchResponse);
        return search;
    }

    @Override
    public String saveSearch(String userId, QuickSearchResponse quickSearchResponse, String type) {
        System.out.println("In saveSearch");
        //so we basically wanna save the search response but we only have up to 5 responses per user
        Map<Long, Double> summarys = new HashMap<>();
        Map<Long, Integer> counts = new HashMap<>();
        for (SearchResult searchResult : quickSearchResponse.getResult()){
            if (summarys.containsKey(searchResult.getCorrelationWith())){
                summarys.put(
                        searchResult.getCorrelationWith(),
                        summarys.get(searchResult.getCorrelationWith())+searchResult.getCorrelation()
                );
                if (counts.containsKey(searchResult.getCorrelationWith())) {
                    counts.put(
                            searchResult.getCorrelationWith(),
                            counts.get(searchResult.getCorrelationWith()) + 1
                    );
                }
            }else{
                summarys.put(
                        searchResult.getCorrelationWith(),
                        searchResult.getCorrelation()
                );
                counts.put(
                        searchResult.getCorrelationWith(),
                        1
                );
            }
            if (searchResult.getPlaceId() == 9999 ){
                searchResult.setPlace(placeRepositoryV2.findByName(searchResult.getName()));
                searchResult.setPlaceId(searchResult.getPlace().getId());
            }else{
                searchResult.setPlace(placeRepositoryV2.findById(
                        searchResult.getPlaceId()).orElseThrow(() -> new RuntimeException("can't find place"))
                );
            }
        }
        if (type == "collaborative"){
            for (SearchResult searchResult : quickSearchResponse.getResult()){
                searchResult.setCorrelation(
                        summarys.get(searchResult.getCorrelationWith())/counts.get(searchResult.getCorrelationWith())
                );
            }
        }
        QuickSearchHistoryV2 quickSearchHistoryV2 =
                new QuickSearchHistoryV2(userId, quickSearchResponse, new Date());
        List<QuickSearchHistoryV2> quickSearches = quickSearchHistoryRepositoryV2.findAllByUserId(userId);
        if (!quickSearches.isEmpty()){
            if (quickSearches.size() > 4) {
                Collections.sort(quickSearches);
                quickSearchHistoryRepositoryV2.delete(quickSearches.get(0));
            }
        }

        return quickSearchHistoryRepositoryV2.save(quickSearchHistoryV2).getId();
    }

    @Override
    public List<QuickSearchHistoryV2> getLatestQuickSearches(String userId) {
        return quickSearchHistoryRepositoryV2.findAllByUserId(userId);
    }

    @Override
    public String advancedSearch(String userId, com.example.ptuxiakh.model.SolidSearch.AdvancedSearchRequest advancedSearchRequest, String searchId) throws Exception {
        //filter Data
        ArrayList<SearchResult> results = new ArrayList<>();
        QuickSearchHistoryV2 quickSearchHistoryV2 = quickSearchHistoryRepositoryV2.findById(searchId).orElseThrow(()-> new RuntimeException("error"));
        QuickSearchResponse quickSearchResponse = quickSearchHistoryV2.getQuickSearchResponse();
        results = filterPlaces(quickSearchResponse, advancedSearchRequest);
        quickSearchResponse.setResult(results);
        String searchIdResult = quickSearchHistoryRepositoryV2.save(quickSearchHistoryV2).getId();
        return searchIdResult;
    }

    /**
     * returns the User if he exists
     * @param userId
     * @return
     */
    private User getUser(String userId){
        if (userId.isEmpty())
            throw new NullPointerException("userId doesnt exist");
        if (userId == null)
            throw new NullPointerException("userId is null");
        return userRepository.findById(userId).orElseThrow( () -> new NullPointerException("cant find user"));
    }

    private ArrayList<SearchResult> filterPlaces(QuickSearchResponse quickSearchResponse, com.example.ptuxiakh.model.SolidSearch.AdvancedSearchRequest advancedSearchRequest){
        ArrayList<SearchResult> results = new ArrayList<>();
        for (SearchResult searchResult: quickSearchResponse.getResult()){
            if (checkPlace(advancedSearchRequest, searchResult.getPlace())){
                results.add(searchResult);
            }
        }
        return results;
    }

    private boolean checkPlace(com.example.ptuxiakh.model.SolidSearch.AdvancedSearchRequest advancedSearchRequest, Place place){
        if (advancedSearchRequest == null ) return true;

        if (validCategory(advancedSearchRequest.getCategory(), place)){
            if (validRating(advancedSearchRequest.getRating(), place)){
                if (validPrice(advancedSearchRequest.getPrice(), place)){
                    if (validRadius(advancedSearchRequest.getRadius(), advancedSearchRequest.getCenter(), place)) //this will be the radius function
                        return true;
                }
            }
        }
        return false;
    }

    private Boolean validCategory(String category, Place place){
        if (category == null) return true;
        if (category.equalsIgnoreCase("all")) return true;

        if (category.equalsIgnoreCase("drink")){
            if (place.getTypes().contains("night_club")){
                return true;
            }
            if (place.getTypes().contains("bar")){
                return true;
            }
            if (place.getTypes().contains("restaurant")){
                return true;
            }
        }else if (category.equalsIgnoreCase("food")){
            if (place.getTypes().contains("food")){
                return true;
            }
            if (place.getTypes().contains("restaurant")){
                return true;
            }
        }else if (category.equalsIgnoreCase("coffee")){
            if (place.getTypes().contains("food")){
                return true;
            }
            if (place.getTypes().contains("cafe")){
                return true;
            }
        }
        return false;
    }

    private Boolean validRating(int rating, Place place){
        if (rating == 0 || place.getRating() == null) return true;
        if (rating <= place.getRating()){
            return true;
        }
        return false;
    }

    private Boolean validPrice(int price, Place place){
        if (price == 0 || place.getPriceLevel() == 0) return true;
        if (price >= place.getPriceLevel()){
            return true;
        }
        return false;
    }

    private Boolean validRadius(int radius, Location center, Place place){
        Double distance = distance(
                center.getLat(),
                center.getLng(),
                place.getGeometry().getLocation().getLat(),
                place.getGeometry().getLocation().getLng(),
                'K'
        );
        if( radius >= distance ) {
            return true;
        }
        return false;
    }

    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
