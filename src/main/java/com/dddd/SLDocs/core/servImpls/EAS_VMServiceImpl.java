package com.dddd.SLDocs.core.servImpls;

import com.dddd.SLDocs.core.entities.views.EAS_VM;
import com.dddd.SLDocs.core.repositories.EAS_VMRepository;
import com.dddd.SLDocs.core.services.EAS_VMService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EAS_VMServiceImpl implements EAS_VMService {

    private EAS_VMRepository eas_vmRepository;

    public EAS_VMServiceImpl(EAS_VMRepository eas_vmRepository) {
        this.eas_vmRepository = eas_vmRepository;
    }

    @Override
    public List<EAS_VM> getEAS_VM4Data(String semester, String course) {
        return eas_vmRepository.getEAS_VM4(semester, course);
    }

    @Override
    public List<EAS_VM> getEAS_VM13Data(String semester, String course) {
        return eas_vmRepository.getEAS_VM13(semester, course);
    }

}
