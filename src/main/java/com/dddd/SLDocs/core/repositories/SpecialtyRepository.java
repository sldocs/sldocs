package com.dddd.SLDocs.core.repositories;

import com.dddd.SLDocs.core.entities.Faculty;
import com.dddd.SLDocs.core.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpecialtyRepository extends JpaRepository<Specialty,Long> {

    @Query("SELECT u FROM Specialty u WHERE u.name = :name")
    Specialty getSpecialtyByName(@Param("name") String name);
}
