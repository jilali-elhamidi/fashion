package com.store.fashion.dto;
import java.util.List;

public class FastAPIRecommendationResponse {
    private List<String> results;


    public FastAPIRecommendationResponse() {
    }


    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}