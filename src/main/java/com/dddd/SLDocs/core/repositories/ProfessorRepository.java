package com.dddd.SLDocs.core.repositories;

import com.dddd.SLDocs.core.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {

    @Modifying
    @Query("delete from Professor p")
    void deleteAll();

    @Query("SELECT u FROM Professor u WHERE u.name = :name")
    Professor getProfessorByName(@Param("name") String name);

    @Query("SELECT u FROM Professor u WHERE u.id = :id")
    Professor getById(@Param("id") long id);

    @Query("SELECT u.ip_filename FROM Professor u")
    List<String> listIpFilenames();

    @Query(value="SELECT * FROM Professor as u WHERE u.posada IS NULL OR u.posada='' OR u.nauk_stupin IS NULL OR u.nauk_stupin='' " +
            "OR u.vch_zvana IS NULL OR u.vch_zvana='' OR u.stavka IS NULL OR  u.stavka='' OR u.email_address IS NULL " +
            "OR u.email_address='' AND u.name NOT LIKE 'курсов%' AND NOT u.name='' OR u.name IS NULL ORDER BY u.name", nativeQuery = true)
    List<Professor> listUnedited();

    @Query(value="SELECT u FROM Professor u WHERE u.email_address IS NOT NULL OR NOT u.email_address=''")
    List<Professor> listWithEmails();
}
