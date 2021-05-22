package com.dddd.SLDocs.core.services;

import com.dddd.SLDocs.core.entities.Curriculum;

import java.util.List;

public interface CurriculumService {

    List<Curriculum> ListAll();
    void save(Curriculum curriculum);
    void deleteAll();
}
