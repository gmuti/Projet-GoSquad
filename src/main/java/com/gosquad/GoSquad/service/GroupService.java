package com.gosquad.GoSquad.service;

import com.gosquad.GoSquad.entity.Group;

import java.util.List;

public interface GroupService {
    List<Group> getGroups();

    boolean createGroup(Group group);

    boolean updateGroup(Group group);

    boolean deleteGroup(String id);
}
