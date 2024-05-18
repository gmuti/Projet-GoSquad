package com.gosquad.GoSquad.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.gosquad.GoSquad.entity.Group;
import com.gosquad.GoSquad.firebase.FirebaseInitialization;
import com.gosquad.GoSquad.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private FirebaseInitialization firebase;

    @Override
    public List<Group> getGroups() {
        List<Group> response = new ArrayList<>();
        Group group;
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
        try {
            for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
                group = doc.toObject(Group.class);
                group.setId(doc.getId());
                response.add(group);
            }
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean createGroup(Group group) {
        Map<String, Object> docData = getDocData(group);
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
    public boolean updateGroup(Group group) {
        Map<String, Object> docData = getDocData(group);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(group.getId()).set(docData);
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
    public boolean deleteGroup(String id) {
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
        return FirestoreClient.getFirestore().collection("group");
    }

    private static Map<String, Object> getDocData(Group group) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("description", group.getDescription());
        docData.put("creatorId", group.getCreatorId());
        docData.put("name", group.getName());
        return docData;
    }
}
