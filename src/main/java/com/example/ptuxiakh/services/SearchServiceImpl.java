package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.AdvancedSearchRequest;
import com.example.ptuxiakh.model.SearchRequest;
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

    /**
     * This is the quick search, the user asks for a quick search of a place
     *
     * @param userId
     * @return
     */
    @Override
    public ResponseEntity<Object> quickSearh(String userId) {
        if (userId == null)
            throw new NullPointerException("userId null");
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("cant find user"));

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
    public Object advancedSearch(String userId, AdvancedSearchRequest advancedSearchRequest) {
        if (userId == null)
            throw new NullPointerException("userId null");
        if (advancedSearchRequest == null)
            throw new NullPointerException("quickSearch null");
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("cant find user"));

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
}
