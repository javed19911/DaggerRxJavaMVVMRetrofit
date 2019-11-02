package com.example.dagger_rxjava_mvvm_retrofit.ui.main;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {

    @Provides
    NewsAdapter provideNewsAdapter() {
        return new NewsAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(MainActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
