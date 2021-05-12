package com.dddd.SLDocs.core.services;

import com.dddd.SLDocs.core.entities.Discipline;
import com.dddd.SLDocs.core.entities.Faculty;

import java.util.List;

public interface FacultyService {

    List<Faculty> ListAll();
    void save(Faculty faculty);
    Faculty findByName(String name);
}
