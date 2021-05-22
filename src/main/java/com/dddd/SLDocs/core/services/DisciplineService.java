package com.dddd.SLDocs.core.services;

import com.dddd.SLDocs.core.entities.Department;
import com.dddd.SLDocs.core.entities.Discipline;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DisciplineService {

    List<Discipline> ListAll();
    void save(Discipline discipline);
    Discipline findByName(String name);
    void deleteAll();
}
