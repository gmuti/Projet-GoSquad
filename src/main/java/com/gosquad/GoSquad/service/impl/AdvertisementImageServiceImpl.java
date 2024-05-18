package com.gosquad.GoSquad.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.gosquad.GoSquad.entity.AdvertisementImage;
import com.gosquad.GoSquad.firebase.FirebaseInitialization;
import com.gosquad.GoSquad.service.AdvertisementImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdvertisementImageServiceImpl implements AdvertisementImageService {

    @Autowired
    private FirebaseInitialization firebase;

    @Override
    public List<AdvertisementImage> getAdvertisementImages() {
        List<AdvertisementImage> response = new ArrayList<>();
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
        try {
            for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
                AdvertisementImage advertisementImage = doc.toObject(AdvertisementImage.class);
                advertisementImage.setId(doc.getId());
                response.add(advertisementImage);
            }
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean createAdvertisementImage(AdvertisementImage advertisementImage) {
        Map<String, Object> docData = getDocData(advertisementImage);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document().create(docData);
        try {
            return writeResultApiFuture.get() != null;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean updateAdvertisementImage(AdvertisementImage advertisementImage) {
        Map<String, Object> docData = getDocData(advertisementImage);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(advertisementImage.getId()).set(docData);
        try {
            return writeResultApiFuture.get() != null;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean deleteAdvertisementImage(String id) {
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).delete();
        try {
            return writeResultApiFuture.get() != null;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    private CollectionReference getCollection() {
        return FirestoreClient.getFirestore().collection("advertisementImage");
    }

    private static Map<String, Object> getDocData(AdvertisementImage advertisementImage) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("advertisementId", advertisementImage.getAdvertisementId());
        docData.put("imageUrl", advertisementImage.getImageUrl());
        return docData;
    }
}
