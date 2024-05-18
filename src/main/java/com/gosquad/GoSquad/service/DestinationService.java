package com.gosquad.GoSquad.service;

import com.gosquad.GoSquad.entity.Destination;

import java.util.List;

public interface DestinationService {
    List<Destination> getDestinations();

    Boolean createDestination(Destination destination);

    Boolean updateDestination(Destination destination);

    Boolean deleteDestination(String id);
}
