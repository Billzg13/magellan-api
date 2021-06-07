package com.example.ptuxiakh.model.SolidSearch;

import com.example.ptuxiakh.model.auth.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

//quickSearch is exactly the same as a search request what should we do with it?
public class QuickSearch extends SearchRequest implements SimpleRecommender {

    public QuickSearch(User user, String type) {
        this.user = user;
        this.type = type;

    }

    @Override
    public QuickSearchResponse recommend(String pythonBaseUrl) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            String requestUrl = pythonBaseUrl +"/search";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<QuickSearch> entity = new HttpEntity<>(this, headers);

            ResponseEntity<QuickSearchResponse> responseEntity = restTemplate.postForEntity(
                    requestUrl,
                    entity,
                    QuickSearchResponse.class
                    );
            if (responseEntity.getStatusCode().is2xxSuccessful()){
                return responseEntity.getBody();
            }
            return null;
        } catch (Exception exc) {
            //exc.printStackTrace();
            return null;
        }
    }



    //quickSearch.search()



}
