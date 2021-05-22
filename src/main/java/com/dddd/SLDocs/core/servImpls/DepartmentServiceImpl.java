package com.dddd.SLDocs.core.servImpls;

import com.dddd.SLDocs.core.entities.Department;
import com.dddd.SLDocs.core.repositories.DepartmentRepository;
import com.dddd.SLDocs.core.services.DepartmentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> ListAll() {
        return departmentRepository.findAll();
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public Department findByName(String name) {
        return departmentRepository.getDepartmentByName(name);
    }

    @Override
    public void deleteAll() {
        departmentRepository.deleteAll();
    }
}
