package com.dddd.SLDocs.core.servImpls;

import com.dddd.SLDocs.core.entities.Curriculum;
import com.dddd.SLDocs.core.repositories.CurriculumRepository;
import com.dddd.SLDocs.core.services.CurriculumService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CurriculumServiceImpl implements CurriculumService {

    private CurriculumRepository curriculumRepository;

    public CurriculumServiceImpl(CurriculumRepository curriculumRepository) {
        this.curriculumRepository = curriculumRepository;
    }

    @Override
    public List<Curriculum> ListAll() {
        return curriculumRepository.findAll();
    }

    @Override
    public void save(Curriculum curriculum) {
        curriculumRepository.save(curriculum);
    }

}
