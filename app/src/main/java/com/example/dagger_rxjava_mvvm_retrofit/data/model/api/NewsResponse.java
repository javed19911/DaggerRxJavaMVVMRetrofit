package com.example.dagger_rxjava_mvvm_retrofit.data.model.api;

import com.example.dagger_rxjava_mvvm_retrofit.data.model.others.News;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<News> articles;

    //getter


    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<News> getArticles() {
        return articles;
    }


    ///setter

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }
}
