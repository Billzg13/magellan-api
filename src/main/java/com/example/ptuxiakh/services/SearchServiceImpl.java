package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.AdvancedSearchRequest;
import com.example.ptuxiakh.model.SearchRequest;
import com.example.ptuxiakh.model.SolidSearch.AdvancedSearch;
import com.example.ptuxiakh.model.SolidSearch.QuickSearch;
import com.example.ptuxiakh.model.TypeOfSearch;
import com.example.ptuxiakh.model.UserHistory;
import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.repository.AdvancedSearchRepository;
import com.example.ptuxiakh.repository.QuickSearchRepository;
import com.example.ptuxiakh.repository.UserHistoryRepository;
import com.example.ptuxiakh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    QuickSearchRepository quickSearchRepository;

    @Autowired
    AdvancedSearchRepository advancedSearchRepository;

    @Autowired
    UserRepository userRepository;

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
    public ResponseEntity<Object> quickSearh(String userId) {
        User user = getUser(userId);

        SearchRequest searchRequest = new SearchRequest(user, TypeOfSearch.QUICKSEARCH);
        try {
            RestTemplate restTemplate = new RestTemplate();
            String requestUrl = pythonBaseUrl +"/search";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<SearchRequest> entity = new HttpEntity<>(searchRequest, headers);
            ResponseEntity<Object> responseEntity = restTemplate.postForEntity(requestUrl, entity, Object.class);
            if (responseEntity.getStatusCode().is2xxSuccessful())
                return responseEntity;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }

    @Override
    public Object quickSearch2(String userId) throws Exception {
        User user = getUser(userId);
        QuickSearch quickSearch = new QuickSearch(user);
        Object response = quickSearch.recommend(pythonLocalUrl);
        if (response ==  null){
            System.out.println("is response null?");
            //if response is null something went wrong that we dont know off
            throw new Exception("something went wrong with python server");
        }
        return response;
    }

    //quickSearch2
        //User user = getUser(userId)
        //QuickSearch quickSearch = new QuickSearch(user)
        //save it in the database?
        //make post request to python microservice
        //if response is successfull then return appropriate response
        //if response is not successfull then return appropriate response

    @Override
    public Object advancedSearch(String userId, AdvancedSearchRequest advancedSearchRequest) {
        User user = getUser(userId);
        if (advancedSearchRequest == null)
            throw new NullPointerException("quickSearch null");

        SearchRequest searchRequest = new SearchRequest(user, TypeOfSearch.ADVANCED_SEARCH, advancedSearchRequest);
        //This is the point where we send the search to python Service in order to get the result of the search
        //should this be sync or async?
        //for now we save the search in the database

        try {
            RestTemplate restTemplate = new RestTemplate();
            String requestUrl = pythonBaseUrl +"/advanced_search";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<SearchRequest> entity = new HttpEntity<>(searchRequest, headers);
            ResponseEntity<Object> responseEntity = restTemplate.postForEntity(requestUrl, entity, Object.class);
            if (responseEntity.getStatusCode().is2xxSuccessful())
                return responseEntity;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        AdvancedSearchRequest result = advancedSearchRepository.save(advancedSearchRequest);
        if (result != null)
            return result;
        return null;
    }

    @Override
    public void saveSearch(String userId, TypeOfSearch typeOfSearch, AdvancedSearchRequest advancedSearchRequest) {
        if (typeOfSearch.equals(typeOfSearch.QUICKSEARCH)){
            UserHistory userHistory = new UserHistory(userId, typeOfSearch);
            userHistoryRepository.save(userHistory);
            //save quicksearch here
        }else{
            UserHistory userHistory = new UserHistory(userId, typeOfSearch, advancedSearchRequest);
            userHistoryRepository.save(userHistory);
            //save advancedSearch here
        }
    }

    @Override
    public List<UserHistory> getUserHistory(String userId) {
        return userHistoryRepository.findAllById(userId);
    }

    @Override
    public Object advancedSearch2(String userId, com.example.ptuxiakh.model.SolidSearch.AdvancedSearchRequest advancedSearchRequest) {
        User user = getUser(userId);
        if (advancedSearchRequest == null)
            throw new NullPointerException("quickSearch null");
        AdvancedSearch advancedSearch = new AdvancedSearch(advancedSearchRequest);
        advancedSearch.setUser(user);


        //This is the point where we send the search to python Service in order to get the result of the search
        //should this be sync or async?
        //for now we save the search in the database

        try {
            RestTemplate restTemplate = new RestTemplate();
            String requestUrl = pythonLocalUrl +"/advanced_search";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<AdvancedSearch> entity = new HttpEntity<>(advancedSearch, headers);
            ResponseEntity<Object> responseEntity = restTemplate.postForEntity(requestUrl, entity, Object.class);
            if (responseEntity.getStatusCode().is2xxSuccessful())
                return responseEntity.getBody();
                //return responseEntity;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
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
