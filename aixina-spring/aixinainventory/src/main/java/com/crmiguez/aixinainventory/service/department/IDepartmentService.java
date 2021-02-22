package com.crmiguez.aixinainventory.service.department;

import com.crmiguez.aixinainventory.entities.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    public List<Department> findAllDepartments();
    public Optional<Department> findDepartmentById(String departmentId);
    public Department addDepartment(Department Department);
    public Department updateDepartment(Department departmentDetails, Department department);
    public void deleteDepartment (Department department);

}
