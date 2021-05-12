package com.dddd.SLDocs.core.servImpls;

import com.dddd.SLDocs.core.entities.Discipline;
import com.dddd.SLDocs.core.repositories.DisciplineRepository;
import com.dddd.SLDocs.core.services.DisciplineService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DisciplineServiceImpl implements DisciplineService {

    private DisciplineRepository disciplineRepository;

    public DisciplineServiceImpl(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    @Override
    public List<Discipline> ListAll() {
        return disciplineRepository.findAll();
    }

    @Override
    public void save(Discipline discipline) {
        disciplineRepository.save(discipline);
    }

    @Override
    public Discipline findByName(String name) {
        return disciplineRepository.getDisciplineByName(name);
    }
}
