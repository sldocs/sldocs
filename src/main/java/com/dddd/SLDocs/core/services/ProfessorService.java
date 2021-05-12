package com.dddd.SLDocs.core.services;

import com.dddd.SLDocs.core.entities.Faculty;
import com.dddd.SLDocs.core.entities.Professor;

import java.util.List;

public interface ProfessorService {

    List<Professor> ListAll();
    void save(Professor professor);
    Professor findByName(String name);
}
