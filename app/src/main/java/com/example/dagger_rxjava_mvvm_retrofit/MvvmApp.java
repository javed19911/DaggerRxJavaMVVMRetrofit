package com.example.dagger_rxjava_mvvm_retrofit;

import android.app.Activity;
import android.app.Application;


import com.example.dagger_rxjava_mvvm_retrofit.data.remote.ApiModule;
import com.example.dagger_rxjava_mvvm_retrofit.di.component.DaggerAppComponent;
import com.example.dagger_rxjava_mvvm_retrofit.utils.AppLogger;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class MvvmApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .baseURL(ApiModule.BASE_URL)
                .build()
                .inject(this);

        AppLogger.init();

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }
}
