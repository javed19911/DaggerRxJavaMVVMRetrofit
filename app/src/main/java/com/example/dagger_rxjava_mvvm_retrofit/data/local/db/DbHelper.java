
package com.example.dagger_rxjava_mvvm_retrofit.data.local.db;

import androidx.lifecycle.LiveData;

import com.example.dagger_rxjava_mvvm_retrofit.data.model.others.News;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface DbHelper {

    Observable<List<News>> getAllNews();

    Observable<Boolean> deleteAllNews();

    Observable<Boolean> insertNews(final News news);

    Observable<Boolean> insertNewsList(final List<News> newsList);


}
