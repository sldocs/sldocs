package com.dddd.SLDocs.core.repositories;

import com.dddd.SLDocs.core.entities.views.EAS_VM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EAS_VMRepository extends JpaRepository<EAS_VM,Long> {
    @Query(value = "select e from EAS_VM e where e.csem=:semester and e.ccor=:course")
    List<EAS_VM> getEAS_VM4(@Param("semester") String semester, @Param("course") String course);
    @Query(value = "select e from EAS_VM e where e.csem=:semester and not e.ccor=:course")
    List<EAS_VM> getEAS_VM13(@Param("semester") String semester, @Param("course") String course);
}
