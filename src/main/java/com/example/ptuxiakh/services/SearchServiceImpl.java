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
    public Object quickSearch(String userId) throws Exception {
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
