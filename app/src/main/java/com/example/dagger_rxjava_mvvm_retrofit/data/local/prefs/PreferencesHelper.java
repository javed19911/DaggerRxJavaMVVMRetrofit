
package com.example.dagger_rxjava_mvvm_retrofit.data.local.prefs;


import com.example.dagger_rxjava_mvvm_retrofit.data.DataManager;

public interface PreferencesHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);
}
