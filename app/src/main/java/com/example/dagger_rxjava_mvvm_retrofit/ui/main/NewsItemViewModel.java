package com.example.dagger_rxjava_mvvm_retrofit.ui.main;

import androidx.databinding.ObservableField;

import com.example.dagger_rxjava_mvvm_retrofit.data.model.others.News;

public class NewsItemViewModel {
    public final ObservableField<String> author;

    public final ObservableField<String> content;

    public final ObservableField<String> date;

    public final ObservableField<String> imageUrl;

    public final NewsItemViewModelListener mListener;

    public final ObservableField<String> title;

    private final News mNews;

    public NewsItemViewModel(News news, NewsItemViewModelListener listener) {
        this.mNews = news;
        this.mListener = listener;
        imageUrl = new ObservableField<>(mNews.getUrlToImage());
        title = new ObservableField<>(mNews.getTitle());
        author = new ObservableField<>(mNews.getAuthor());
        date = new ObservableField<>(mNews.getPublishedAt());
        content = new ObservableField<>(mNews.getDescription());
    }

    public void onItemClick() {
        mListener.onItemClick(mNews.getUrl());
    }

    public interface NewsItemViewModelListener {

        void onItemClick(String blogUrl);
    }

}
