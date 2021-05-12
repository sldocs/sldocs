package com.dddd.SLDocs.core.servImpls;

import com.dddd.SLDocs.core.entities.Specialty;
import com.dddd.SLDocs.core.repositories.SpecialtyRepository;
import com.dddd.SLDocs.core.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SpecialtyServiceImpl implements SpecialtyService {

    private SpecialtyRepository specialtyRepository;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public List<Specialty> ListAll() {
        return specialtyRepository.findAll();
    }

    @Override
    public void save(Specialty specialty) {
        specialtyRepository.save(specialty);
    }

    @Override
    public Specialty findByName(String name) {
        return specialtyRepository.getSpecialtyByName(name);
    }
}
