package com.dddd.SLDocs.core.repositories;

import com.dddd.SLDocs.core.entities.Department;
import com.dddd.SLDocs.core.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    @Query("SELECT u FROM Faculty u WHERE u.name = :name")
    Faculty getFacultyByName(@Param("name") String name);
}
