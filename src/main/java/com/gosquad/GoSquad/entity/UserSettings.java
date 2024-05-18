package com.gosquad.GoSquad.entity;

import lombok.Data;

@Data
public class UserSettings {
    private String id;
    private String notificationPreferences;
    private String privacySettings;
    private String userId;
}
