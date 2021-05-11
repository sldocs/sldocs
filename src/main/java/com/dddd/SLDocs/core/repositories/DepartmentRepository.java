package com.dddd.SLDocs.core.repositories;

import com.dddd.SLDocs.core.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
