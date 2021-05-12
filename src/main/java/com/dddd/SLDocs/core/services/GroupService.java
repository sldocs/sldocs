package com.dddd.SLDocs.core.services;

import com.dddd.SLDocs.core.entities.Group;

import java.util.List;

public interface GroupService {

    List<Group> ListAll();
    void save(Group group);
    Group findByName(String name);
}
