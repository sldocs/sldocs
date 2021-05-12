package com.dddd.SLDocs.core.repositories;

import com.dddd.SLDocs.core.entities.Department;
import com.dddd.SLDocs.core.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    @Query("SELECT u FROM Department u WHERE u.name = :name")
    Department getDepartmentByName(@Param("name") String name);
}
