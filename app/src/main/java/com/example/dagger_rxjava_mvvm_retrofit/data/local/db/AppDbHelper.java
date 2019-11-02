package com.example.dagger_rxjava_mvvm_retrofit.data.local.db;

import androidx.lifecycle.LiveData;

import com.example.dagger_rxjava_mvvm_retrofit.data.model.others.News;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<List<News>> getAllNews() {
        return mAppDatabase.newsDao().loadAll().toObservable();
    }

    @Override
    public Observable<Boolean> deleteAllNews() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.newsDao().Truncate();
                return true;
            }
        });

    }

    @Override
    public Observable<Boolean> insertNews(final News news) {
        return Observable.fromCallable(() -> {
            mAppDatabase.newsDao().insert(news);
            return true;
        });
    }


    @Override
    public Observable<Boolean> insertNewsList(final List<News> newsList) {

        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call()  {
                try{
                    mAppDatabase.newsDao().insertAll(newsList);
                }catch (Exception e){
                    e.printStackTrace();
                }

                return true;
            }
        });
    }
}
