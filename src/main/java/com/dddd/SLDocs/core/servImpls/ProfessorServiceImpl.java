package com.dddd.SLDocs.core.servImpls;

import com.dddd.SLDocs.core.entities.Professor;
import com.dddd.SLDocs.core.repositories.ProfessorRepository;
import com.dddd.SLDocs.core.services.ProfessorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

    private ProfessorRepository professorRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Professor> ListAll() {
        return professorRepository.findAll();
    }

    @Override
    public void save(Professor professor) {
        professorRepository.save(professor);
    }

    @Override
    public Professor findByName(String name) {
        return professorRepository.getProfessorByName(name);
    }
}
