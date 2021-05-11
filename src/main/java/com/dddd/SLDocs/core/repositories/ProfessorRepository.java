package com.dddd.SLDocs.core.repositories;

import com.dddd.SLDocs.core.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
