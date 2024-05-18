package com.gosquad.GoSquad.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.gosquad.GoSquad.entity.UserSettings;
import com.gosquad.GoSquad.firebase.FirebaseInitialization;
import com.gosquad.GoSquad.service.UserSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserSettingsServiceImpl implements UserSettingsService {

    @Autowired
    private FirebaseInitialization firebase;

    @Override
    public List<UserSettings> getUserSettings() {
        List<UserSettings> response = new ArrayList<>();
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
        try {
            for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
                UserSettings userSettings = doc.toObject(UserSettings.class);
                userSettings.setId(doc.getId());
                response.add(userSettings);
            }
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean createUserSettings(UserSettings userSettings) {
        Map<String, Object> docData = getDocData(userSettings);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document().create(docData);
        try {
            return writeResultApiFuture.get() != null;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean updateUserSettings(UserSettings userSettings) {
        Map<String, Object> docData = getDocData(userSettings);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(userSettings.getId()).set(docData);
        try {
            return writeResultApiFuture.get() != null;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean deleteUserSettings(String id) {
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).delete();
        try {
            return writeResultApiFuture.get() != null;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    private CollectionReference getCollection() {
        return FirestoreClient.getFirestore().collection("userSettings");
    }

    private static Map<String, Object> getDocData(UserSettings userSettings) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("notificationPreferences", userSettings.getNotificationPreferences());
        docData.put("privacySettings", userSettings.getPrivacySettings());
        docData.put("userId", userSettings.getUserId());
        return docData;
    }
}
