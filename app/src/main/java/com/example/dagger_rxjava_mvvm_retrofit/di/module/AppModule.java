package com.example.dagger_rxjava_mvvm_retrofit.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.dagger_rxjava_mvvm_retrofit.R;
import com.example.dagger_rxjava_mvvm_retrofit.data.AppDataManager;
import com.example.dagger_rxjava_mvvm_retrofit.data.DataManager;
import com.example.dagger_rxjava_mvvm_retrofit.data.local.db.AppDatabase;
import com.example.dagger_rxjava_mvvm_retrofit.data.local.db.AppDbHelper;
import com.example.dagger_rxjava_mvvm_retrofit.data.local.db.DbHelper;
import com.example.dagger_rxjava_mvvm_retrofit.data.local.prefs.AppPreferencesHelper;
import com.example.dagger_rxjava_mvvm_retrofit.data.local.prefs.PreferencesHelper;
import com.example.dagger_rxjava_mvvm_retrofit.di.DatabaseInfo;
import com.example.dagger_rxjava_mvvm_retrofit.di.PreferenceInfo;
import com.example.dagger_rxjava_mvvm_retrofit.utils.AppConstants;
import com.example.dagger_rxjava_mvvm_retrofit.utils.rx.AppSchedulerProvider;
import com.example.dagger_rxjava_mvvm_retrofit.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class AppModule {


    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }


    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }


    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

}
