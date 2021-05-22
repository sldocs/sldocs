package com.dddd.SLDocs.core.repositories;

import com.dddd.SLDocs.core.entities.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CurriculumRepository extends JpaRepository<Curriculum,Long> {
    @Modifying
    @Query("delete from Curriculum c")
    void deleteAll();
}
