package com.dddd.SLDocs.core.servImpls;

import com.dddd.SLDocs.core.entities.views.PSL_VM;
import com.dddd.SLDocs.core.repositories.PSL_VMRepository;
import com.dddd.SLDocs.core.services.PSL_VMService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PLS_VMServiceImpl implements PSL_VMService {

    private final PSL_VMRepository psl_vmRepository;

    public PLS_VMServiceImpl(PSL_VMRepository psl_vmRepository) {
        this.psl_vmRepository = psl_vmRepository;
    }

    @Override
    public List<PSL_VM> getPSL_VMData(String semester, String pname) {
        return psl_vmRepository.getPSL_VM(semester, pname);
    }
}
