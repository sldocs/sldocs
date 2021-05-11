package com.dddd.SLDocs.core.repositories;

import com.dddd.SLDocs.core.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Long> {

    @Query("SELECT u FROM Group u WHERE u.name = :name")
    Group getGroupByName(@Param("name") String name);

}
