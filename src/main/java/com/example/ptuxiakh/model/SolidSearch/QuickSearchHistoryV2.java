package com.example.ptuxiakh.model.SolidSearch;


import org.springframework.data.annotation.Id;

import java.util.Date;

public class QuickSearchHistoryV2 implements Comparable< QuickSearchHistoryV2 >{

    @Id
    private String id;

    private String userId;

    QuickSearchResponse quickSearchResponse;

    Date dateCreated;

    public QuickSearchHistoryV2() {
    }

    public QuickSearchHistoryV2(String userId, QuickSearchResponse quickSearchResponse, Date dateCreated) {
        this.userId = userId;
        this.quickSearchResponse = quickSearchResponse;
        this.dateCreated = dateCreated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public QuickSearchResponse getQuickSearchResponse() {
        return quickSearchResponse;
    }

    public void setQuickSearchResponse(QuickSearchResponse quickSearchResponse) {
        this.quickSearchResponse = quickSearchResponse;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public int compareTo(QuickSearchHistoryV2 o) {
        return this.getDateCreated().compareTo(o.getDateCreated());
    }

    @Override
    public String toString() {
        return "QuickSearchHistoryV2{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", quickSearchResponse=" + quickSearchResponse +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
