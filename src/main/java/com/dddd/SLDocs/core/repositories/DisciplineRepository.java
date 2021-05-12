package com.dddd.SLDocs.core.repositories;

import com.dddd.SLDocs.core.entities.Department;
import com.dddd.SLDocs.core.entities.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DisciplineRepository extends JpaRepository<Discipline,Long> {

    @Query("SELECT u FROM Discipline u WHERE u.name = :name")
    Discipline getDisciplineByName(@Param("name") String name);
}
