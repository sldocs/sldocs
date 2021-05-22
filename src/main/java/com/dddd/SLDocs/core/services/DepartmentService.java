package com.dddd.SLDocs.core.services;

import com.dddd.SLDocs.core.entities.Curriculum;
import com.dddd.SLDocs.core.entities.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> ListAll();
    void save(Department department);
    Department findByName(String name);
    void deleteAll();
}
