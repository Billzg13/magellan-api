package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.AdvancedSearch;
import com.example.ptuxiakh.model.QuickSearch;
import com.example.ptuxiakh.repository.AdvancedSearchRepository;
import com.example.ptuxiakh.repository.QuickSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    QuickSearchRepository quickSearchRepository;

    @Autowired
    AdvancedSearchRepository advancedSearchRepository;

    /**
     * This is the quick search, the user asks for a quick search of a place
     *
     * @param userId
     * @param quickSearch
     * @return
     */
    @Override
    public Object quickSearh(String userId, QuickSearch quickSearch) {
        if (userId == null)
            throw new NullPointerException("userId null");
        if (quickSearch == null)
            throw new NullPointerException("quickSearch null");

        //This is the point where we send the search to python Service in order to get the result of the search
        //should this be sync or async?
        //for now we save the search in the database
        QuickSearch result = quickSearchRepository.save(quickSearch);
        if (result != null)
            return result;
        return null;
    }

    @Override
    public Object advancedSearch(String userId, AdvancedSearch advancedSearch) {
        if (userId == null)
            throw new NullPointerException("userId null");
        if (advancedSearch == null)
            throw new NullPointerException("quickSearch null");

        //This is the point where we send the search to python Service in order to get the result of the search
        //should this be sync or async?
        //for now we save the search in the database
        AdvancedSearch result = advancedSearchRepository.save(advancedSearch);
        if (result != null)
            return result;
        return null;
    }
}
