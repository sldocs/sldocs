package com.dddd.SLDocs.core.servImpls;

import com.dddd.SLDocs.core.entities.Faculty;
import com.dddd.SLDocs.core.repositories.FacultyRepository;
import com.dddd.SLDocs.core.services.FacultyService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<Faculty> ListAll() {
        return facultyRepository.findAll();
    }

    @Override
    public void save(Faculty faculty) {
        facultyRepository.save(faculty);
    }

    @Override
    public Faculty findByName(String name) {
        return facultyRepository.getFacultyByName(name);
    }

    @Override
    public void deleteAll() {
        facultyRepository.deleteAll();
    }
}
