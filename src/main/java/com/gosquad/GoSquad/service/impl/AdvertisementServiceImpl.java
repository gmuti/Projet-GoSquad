
package com.gosquad.GoSquad.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.gosquad.GoSquad.entity.Advertisement;
import com.gosquad.GoSquad.firebase.FirebaseInitialization;
import com.gosquad.GoSquad.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private FirebaseInitialization firebase;

    public List<Advertisement> getAdvertisements() {
        List<Advertisement> response = new ArrayList<>();
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
        try {
            for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
                Advertisement advertisement = doc.toObject(Advertisement.class);
                advertisement.setId(doc.getId());
                response.add(advertisement);
            }
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean createAdvertisement(Advertisement advertisement) {
        Map<String, Object> docData = getDocData(advertisement);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document().create(docData);
        try {
            return writeResultApiFuture.get() != null;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean updateAdvertisement(Advertisement advertisement) {
        Map<String, Object> docData = getDocData(advertisement);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(advertisement.getId()).set(docData);
        try {
            return writeResultApiFuture.get() != null;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean deleteAdvertisement(Long id) {
        return null;
    }

    @Override
    public Boolean deleteAdvertisement(String id) {
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).delete();
        try {
            return writeResultApiFuture.get() != null;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    private CollectionReference getCollection() {
        return FirestoreClient.getFirestore().collection("advertisement");
    }

    private static Map<String, Object> getDocData(Advertisement advertisement) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("description", advertisement.getDescription());
        docData.put("image", advertisement.getImage());
        docData.put("link", advertisement.getLink());
        docData.put("price", advertisement.getPrice());
        docData.put("source", advertisement.getSource());
        docData.put("title", advertisement.getTitle());
        return docData;
    }
}
