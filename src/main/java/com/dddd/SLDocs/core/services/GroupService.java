package com.dddd.SLDocs.core.services;

import com.dddd.SLDocs.core.entities.Group;
import org.apache.batik.ext.awt.image.GammaTransfer;

import java.util.List;

public interface GroupService {

    List<Group> ListAll();

    void save(Group group);
    Group findByName(String name);
}
