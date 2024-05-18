package com.gosquad.GoSquad.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.gosquad.GoSquad.entity.Activity;
import com.gosquad.GoSquad.firebase.FirebaseInitialization;
import com.gosquad.GoSquad.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private FirebaseInitialization firebase;

    @Override
    public List<Activity> getActivities() {
        List<Activity> response = new ArrayList<>();
        Activity activity;
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
        try {
            for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
                activity = doc.toObject(Activity.class);
                activity.setId(doc.getId());
                response.add(activity);
            }
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean createActivity(Activity activity) {
        Map<String, Object> docData = getDocData(activity);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document().create(docData);
        try {
            if (null != writeResultApiFuture.get()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean updateActivity(Activity activity) {
        Map<String, Object> docData = getDocData(activity);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(activity.getId()).set(docData);
        try {
            if (null != writeResultApiFuture.get()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean deleteActivity(String id) {
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).delete();
        try {
            if (null != writeResultApiFuture.get()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    private CollectionReference getCollection() {
        return FirestoreClient.getFirestore().collection("activity");
    }

    private static Map<String, Object> getDocData(Activity activity) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("description", activity.getDescription());
        docData.put("groupId", activity.getGroupId());
        docData.put("name", activity.getName());
        return docData;
    }
}
