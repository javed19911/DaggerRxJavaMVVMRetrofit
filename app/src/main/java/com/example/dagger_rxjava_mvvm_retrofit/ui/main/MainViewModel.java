package com.example.dagger_rxjava_mvvm_retrofit.ui.main;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dagger_rxjava_mvvm_retrofit.data.DataManager;
import com.example.dagger_rxjava_mvvm_retrofit.data.model.api.NewsResponse;
import com.example.dagger_rxjava_mvvm_retrofit.data.model.others.News;
import com.example.dagger_rxjava_mvvm_retrofit.ui.base.BaseViewModel;
import com.example.dagger_rxjava_mvvm_retrofit.utils.AppLogger;
import com.example.dagger_rxjava_mvvm_retrofit.utils.rx.SchedulerProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private final MutableLiveData<List<News>> NewsListLiveData;

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        NewsListLiveData = new MutableLiveData<>();
        fetchNewsDb();
        fetchNews();
    }

    public void fetchNewsDb() {
        getCompositeDisposable().add(getDataManager()
                .getAllNews()
                .doOnNext(list -> Log.d("NEWS", "loadNews: " + list.size()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(questionList -> {
                    if (questionList != null) {
                        Log.d("NEWS", "loadNews: " + questionList.size());
                        NewsListLiveData.setValue(questionList);
                    }
                }, throwable -> {
                    Log.d("NEWS", "loadNews: " + throwable);
                }));
    }
    public void fetchNews() {


        setIsLoading(true);
        getDataManager().getHeadlines()
                .enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                    if (response.isSuccessful()){
                        NewsResponse newsList = response.body();
                        AppLogger.d(newsList.toString());
                        if (newsList != null && newsList.getArticles().size()>0) {
                            getDataManager().insertNewsList(newsList.getArticles())
                                    .subscribeOn(getSchedulerProvider().io())
                                    .observeOn(getSchedulerProvider().ui())
                                    .subscribe();
                            NewsListLiveData.setValue(newsList.getArticles());
                        }
                    }
                    setIsLoading(false);
                }

                @Override
                public void onFailure(Call<NewsResponse> call, Throwable throwable) {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }
            });
    }

    public LiveData<List<News>> getNewsListLiveData() {
        return NewsListLiveData;
    }


}
