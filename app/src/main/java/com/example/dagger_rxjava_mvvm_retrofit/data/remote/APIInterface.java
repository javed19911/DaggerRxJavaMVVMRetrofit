package com.example.dagger_rxjava_mvvm_retrofit.data.remote;

import com.example.dagger_rxjava_mvvm_retrofit.data.model.api.NewsResponse;
import com.example.dagger_rxjava_mvvm_retrofit.data.model.others.News;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("top-headlines")
    Call<NewsResponse> getHeadlines(@Query("country") String country, @Query("apiKey") String apiKey);
}
