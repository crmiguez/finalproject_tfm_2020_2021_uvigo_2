package com.crmiguez.aixinainventory.repository;

import com.crmiguez.aixinainventory.entities.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("departmentRepository")
public interface DepartmentRepository extends CrudRepository<Department, String> {
    Department findByDepartmentId(String departmentId);
}
