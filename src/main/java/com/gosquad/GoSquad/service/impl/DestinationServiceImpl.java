package com.gosquad.GoSquad.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.gosquad.GoSquad.entity.Destination;
import com.gosquad.GoSquad.firebase.FirebaseInitialization;
import com.gosquad.GoSquad.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private FirebaseInitialization firebase;

    @Override
    public List<Destination> getDestinations() {
        List<Destination> response = new ArrayList<>();
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
        try {
            for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
                Destination destination = doc.toObject(Destination.class);
                destination.setId(doc.getId());
                response.add(destination);
            }
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean createDestination(Destination destination) {
        Map<String, Object> docData = getDocData(destination);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document().create(docData);
        try {
            if(null != writeResultApiFuture.get()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean updateDestination(Destination destination) {
        Map<String, Object> docData = getDocData(destination);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(destination.getId()).set(docData);
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
    public Boolean deleteDestination(String id) {
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).delete();
        try {
            if(null != writeResultApiFuture.get()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    private CollectionReference getCollection() {
        return FirestoreClient.getFirestore().collection("destination");
    }

    private static Map<String, Object> getDocData(Destination destination) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("description", destination.getDescription());
        docData.put("groupId", destination.getGroupId());
        docData.put("name", destination.getName());
        return docData;
    }
}
