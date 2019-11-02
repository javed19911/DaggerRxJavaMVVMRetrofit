
package com.example.dagger_rxjava_mvvm_retrofit.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.dagger_rxjava_mvvm_retrofit.data.local.db.DbHelper;
import com.example.dagger_rxjava_mvvm_retrofit.data.local.prefs.PreferencesHelper;
import com.example.dagger_rxjava_mvvm_retrofit.data.model.api.NewsResponse;
import com.example.dagger_rxjava_mvvm_retrofit.data.model.others.News;
import com.example.dagger_rxjava_mvvm_retrofit.data.remote.APIInterface;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Retrofit;

import static com.example.dagger_rxjava_mvvm_retrofit.data.remote.ApiModule.API_Country;
import static com.example.dagger_rxjava_mvvm_retrofit.data.remote.ApiModule.API_KEY;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private Retrofit mRetrofit;

    private final DbHelper mDbHelper;

    private final Context mContext;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper, Retrofit retrofit) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mRetrofit = retrofit;
    }


    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
    }

    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPreferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPreferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public Call<NewsResponse> getHeadlines() {
        return mRetrofit
                .create(APIInterface.class)
                .getHeadlines(API_Country,API_KEY);
    }

    @Override
    public Observable<List<News>> getAllNews() {
        return mDbHelper.getAllNews();
    }


    @Override
    public Observable<Boolean> deleteAllNews() {
        return mDbHelper.deleteAllNews();
    }

    @Override
    public Observable<Boolean> insertNews(News news) {
        return mDbHelper.insertNews(news);
    }

    @Override
    public Observable<Boolean> insertNewsList(List<News> newsList) {
        return mDbHelper.insertNewsList(newsList);
    }
}
