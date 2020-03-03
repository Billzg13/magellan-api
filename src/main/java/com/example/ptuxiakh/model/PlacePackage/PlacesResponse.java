package com.example.ptuxiakh.model.PlacePackage;

import java.util.ArrayList;
import java.util.List;

public class PlacesResponse {

    List<Object> html_attributions;
    public String next_page_token;
    ArrayList<Place> results;
    String status;

    public PlacesResponse() {
    }

    public PlacesResponse(List<Object> html_attributions, String next_page_token, ArrayList<Place> results, String status) {
        this.html_attributions = html_attributions;
        this.next_page_token = next_page_token;
        this.results = results;
        this.status = status;
    }

    public List<Object> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<Object> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    public ArrayList<Place> getResults() {
        return results;
    }

    public void setResults(ArrayList<Place> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PlacesResponse{" +
                "html_attributions=" + html_attributions +
                ", next_page_token='" + next_page_token + '\'' +
                ", results=" + results +
                ", status='" + status + '\'' +
                '}';
    }
}
