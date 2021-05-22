package com.dddd.SLDocs.core.services;

import com.dddd.SLDocs.core.entities.Professor;
import com.dddd.SLDocs.core.entities.Specialty;

import java.util.List;

public interface SpecialtyService {

    List<Specialty> ListAll();
    void save(Specialty specialty);
    Specialty findByName(String name);
    void deleteAll();

}
