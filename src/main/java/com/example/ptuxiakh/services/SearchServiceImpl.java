package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.AdvancedSearch;
import com.example.ptuxiakh.model.SearchRequest;
import com.example.ptuxiakh.model.TypeOfSearch;
import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.repository.AdvancedSearchRepository;
import com.example.ptuxiakh.repository.QuickSearchRepository;
import com.example.ptuxiakh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    QuickSearchRepository quickSearchRepository;

    @Autowired
    AdvancedSearchRepository advancedSearchRepository;

    @Autowired
    UserRepository userRepository;

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
        User user = userRepository.findById(userId).orElseThrow(()-> new NullPointerException("cant find user"));

        SearchRequest searchRequest = new SearchRequest(user, TypeOfSearch.QUICKSEARCH);
        try {
            RestTemplate restTemplate = new RestTemplate();
            String requestUrl = "http://localhost:5000/search";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<SearchRequest> entity = new HttpEntity<>(searchRequest, headers);
            ResponseEntity<Object> responseEntity = restTemplate.postForEntity(requestUrl, entity, Object.class);
            if (responseEntity.getStatusCode().is2xxSuccessful())
                return responseEntity;
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return null;

    }

    @Override
    public Object advancedSearch(String userId, AdvancedSearch advancedSearch) {
        if (userId == null)
            throw new NullPointerException("userId null");
        if (advancedSearch == null)
            throw new NullPointerException("quickSearch null");
        User user = userRepository.findById(userId).orElseThrow(()-> new NullPointerException("cant find user"));
        SearchRequest searchRequest = new SearchRequest(user, TypeOfSearch.ADVANCED_SEARCH, advancedSearch);
        //This is the point where we send the search to python Service in order to get the result of the search
        //should this be sync or async?
        //for now we save the search in the database
        AdvancedSearch result = advancedSearchRepository.save(advancedSearch);
        if (result != null)
            return result;
        return null;
    }
}
