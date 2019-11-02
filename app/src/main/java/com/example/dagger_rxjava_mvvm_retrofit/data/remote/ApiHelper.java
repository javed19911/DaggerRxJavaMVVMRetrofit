package com.example.dagger_rxjava_mvvm_retrofit.data.remote;

import com.example.dagger_rxjava_mvvm_retrofit.data.model.api.NewsResponse;
import com.example.dagger_rxjava_mvvm_retrofit.data.model.others.News;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;

public interface ApiHelper {

    Call<NewsResponse> getHeadlines();
}
