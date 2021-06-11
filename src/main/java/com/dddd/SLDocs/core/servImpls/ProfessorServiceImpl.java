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

    private final ProfessorRepository professorRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Professor> ListAll() {
        return professorRepository.findAll();
    }

    @Override
    public List<Professor> listAllOrderName() {
        return professorRepository.listAllOrderName();
    }

    @Override
    public void save(Professor professor) {
        professorRepository.save(professor);
    }

    @Override
    public Professor findByName(String name) {
        return professorRepository.getProfessorByName(name);
    }

    @Override
    public Professor getByID(long id) {
        return professorRepository.getById(id);
    }

    @Override
    public List<String> listIpFilenames(){
        return professorRepository.listIpFilenames();
    }

    @Override
    public List<Professor> listUnedited() {
        return professorRepository.listUnedited();
    }

    @Override
    public void deleteAll() {
        professorRepository.deleteAll();
    }

    @Override
    public List<Professor> listWithEmails() {
        return professorRepository.listWithEmails();
    }

}
