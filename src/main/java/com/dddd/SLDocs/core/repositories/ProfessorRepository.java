package com.dddd.SLDocs.core.repositories;

import com.dddd.SLDocs.core.entities.Faculty;
import com.dddd.SLDocs.core.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {

    @Query("SELECT u FROM Professor u WHERE u.name = :name")
    Professor getProfessorByName(@Param("name") String name);
}
