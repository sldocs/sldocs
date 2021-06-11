package com.dddd.SLDocs.core.services;

import com.dddd.SLDocs.core.entities.Faculty;
import com.dddd.SLDocs.core.entities.Professor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorService {

    List<Professor> ListAll();
    List<Professor> listAllOrderName();
    void save(Professor professor);
    Professor findByName(String name);
    Professor getByID(long id);
    List<String> listIpFilenames();
    List<Professor> listUnedited();
    void deleteAll();
    List<Professor> listWithEmails();

}
