package com.gosquad.GoSquad.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.gosquad.GoSquad.entity.User;
import com.gosquad.GoSquad.firebase.FirebaseInitialization;
import com.gosquad.GoSquad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private FirebaseInitialization firebase;

    public List<User> getUsers() {
        List<User> response = new ArrayList<>();
        User user;
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
        try {
            for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
                user = doc.toObject(User.class);
                user.setId(doc.getId());
                response.add(user);
            }
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean createUser(User user) {
        Map<String, Object> docData = getDocData(user);
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
    public Boolean updateUser(User user) {
        Map<String, Object> docData = getDocData(user);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(user.getId()).set(docData);
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
    public Boolean deleteUser(String id) {
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
        return FirestoreClient.getFirestore().collection("user");
    }

    private static Map<String, Object> getDocData(User user) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("name", user.getName());
        docData.put("mail", user.getMail());
        docData.put("password", user.getPassword());
        return docData;
    }
}
