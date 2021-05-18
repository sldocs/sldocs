package com.dddd.SLDocs.core.services;

import com.dddd.SLDocs.core.entities.views.PSL_VM;

import java.util.List;

public interface PSL_VMService {

    List<PSL_VM> getPSL_VMData(String semester, String pname);
}
