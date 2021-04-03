package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.SolidSearch.AdvancedSearch;
import com.example.ptuxiakh.model.SolidSearch.QuickSearch;
import com.example.ptuxiakh.model.SolidSearch.QuickSearchHistoryV2;
import com.example.ptuxiakh.model.SolidSearch.QuickSearchResponse;
import com.example.ptuxiakh.model.auth.User;
import com.example.ptuxiakh.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    QuickSearchHistoryRepositoryV2 quickSearchHistoryRepositoryV2;

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
    public Boolean saveSearch(String userId, QuickSearchResponse quickSearchResponse) {
        //so we basically wanna save the search response but we only have up to 5 responses per user
        com.example.ptuxiakh.model.SolidSearch.QuickSearchHistoryV2 quickSearchHistoryV2 =
                new com.example.ptuxiakh.model.SolidSearch.QuickSearchHistoryV2(userId, quickSearchResponse, new Date());
        List<QuickSearchHistoryV2> quickSearches = quickSearchHistoryRepositoryV2.findAllByUserId(userId);
        if (!quickSearches.isEmpty()){
            if (quickSearches.size() > 4) {
                Collections.sort(quickSearches);
                for ( QuickSearchHistoryV2 quickSearchHistoryV21 :quickSearches.subList(4, quickSearches.size())){
                    quickSearchHistoryRepositoryV2.delete(quickSearchHistoryV21);
                }
            }
        }
        quickSearchHistoryRepositoryV2.save(quickSearchHistoryV2);
        return null;
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
