package com.gosquad.GoSquad.service;

import com.gosquad.GoSquad.entity.UserSettings;

import java.util.List;

public interface UserSettingsService {
    List<UserSettings> getUserSettings();

    Boolean createUserSettings(UserSettings userSettings);

    Boolean updateUserSettings(UserSettings userSettings);

    Boolean deleteUserSettings(String id);
}
