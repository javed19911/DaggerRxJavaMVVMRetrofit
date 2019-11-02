package com.example.dagger_rxjava_mvvm_retrofit.di.component;

import android.app.Application;


import com.example.dagger_rxjava_mvvm_retrofit.MvvmApp;
import com.example.dagger_rxjava_mvvm_retrofit.data.remote.ApiModule;
import com.example.dagger_rxjava_mvvm_retrofit.di.builder.ActivityBuilder;
import com.example.dagger_rxjava_mvvm_retrofit.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ApiModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(MvvmApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder baseURL(String Url);

        AppComponent build();
    }
}
