package com.dddd.SLDocs.core.repositories;

import com.dddd.SLDocs.core.entities.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CurriculumRepository extends JpaRepository<Curriculum,Long> {
}
