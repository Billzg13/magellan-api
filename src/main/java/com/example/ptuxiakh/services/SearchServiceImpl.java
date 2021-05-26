package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.SearchResult;
import com.example.ptuxiakh.model.SolidSearch.AdvancedSearch;
import com.example.ptuxiakh.model.SolidSearch.QuickSearch;
import com.example.ptuxiakh.model.SolidSearch.QuickSearchHistoryV2;
import com.example.ptuxiakh.model.SolidSearch.QuickSearchResponse;
import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public QuickSearchResponse quickSearch(String userId) throws Exception {
        User user = getUser(userId);
        QuickSearch quickSearch = new QuickSearch(user);
        QuickSearchResponse response = quickSearch.recommend(pythonLocalUrl);
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
        //for (SearchResult searchResult :search.getQuickSearchResponse().getResult()){
        //    searchResult.setPlace(placeRepositoryV2.findById(searchResult.getPlaceId()).orElseThrow(() -> new RuntimeException("can't find place")));
        //}
        return search;
    }

    @Override
    public String saveSearch(String userId, QuickSearchResponse quickSearchResponse) {
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
            }
        }
        for (SearchResult searchResult : quickSearchResponse.getResult()){
            searchResult.setCorrelation(
                    summarys.get(searchResult.getCorrelationWith())/counts.get(searchResult.getCorrelationWith())
            );
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
    public Object advancedSearch(String userId, com.example.ptuxiakh.model.SolidSearch.AdvancedSearchRequest advancedSearchRequest) throws Exception {
        User user = getUser(userId);
        if (advancedSearchRequest == null)
            throw new NullPointerException("quickSearch null");
        AdvancedSearch advancedSearch = new AdvancedSearch(advancedSearchRequest);
        advancedSearch.setUser(user);
        Object response = advancedSearch.recommend(pythonBaseUrl);
        if (response == null)
            throw new Exception("something went wroong on python");
        return response;
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
}
