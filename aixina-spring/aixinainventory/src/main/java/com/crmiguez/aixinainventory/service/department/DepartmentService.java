package com.crmiguez.aixinainventory.service.department;

import com.crmiguez.aixinainventory.entities.Department;
import com.crmiguez.aixinainventory.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("departmentService")
public class DepartmentService implements IDepartmentService {

    @Autowired
    @Qualifier("departmentRepository")
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAllDepartments() {
        return (List<Department>) departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findDepartmentById(String departmentId){
        return departmentRepository.findById(departmentId);
    }

    @Override
    public Department addDepartment(Department department){
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment (Department department){
        departmentRepository.delete(department);
    }

    @Override
    public Department updateDepartment(Department departmentDetails, Department department){

        department.setDepartmentName(departmentDetails.getDepartmentName());
        department.setHeadquarters(departmentDetails.getHeadquarters());

        return departmentRepository.save(department);
    }
}
