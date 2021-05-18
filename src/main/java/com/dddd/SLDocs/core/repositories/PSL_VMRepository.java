package com.dddd.SLDocs.core.repositories;

import com.dddd.SLDocs.core.entities.views.PSL_VM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PSL_VMRepository extends JpaRepository<PSL_VM, Long> {
    @Query(value = "select p from PSL_VM p where p.csem=:semester and p.pname=:pname")
    List<PSL_VM> getPSL_VM(@Param("semester") String semester, @Param("pname") String pname);

}
