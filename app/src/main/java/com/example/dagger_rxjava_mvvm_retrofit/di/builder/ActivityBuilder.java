
package com.example.dagger_rxjava_mvvm_retrofit.di.builder;

import com.example.dagger_rxjava_mvvm_retrofit.ui.main.MainActivity;
import com.example.dagger_rxjava_mvvm_retrofit.ui.main.NewsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            NewsModule.class})
    abstract MainActivity bindMainActivity();
}
