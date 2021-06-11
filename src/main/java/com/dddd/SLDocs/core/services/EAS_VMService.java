package com.dddd.SLDocs.core.services;

import com.dddd.SLDocs.core.entities.views.EAS_VM;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EAS_VMService {

    List<EAS_VM> getEAS_VM13Data(String semester, String course);
    List<EAS_VM> getEAS_VMData(String semester, String course);

}
