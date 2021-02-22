package com.crmiguez.aixinainventory.service.employee;

import com.crmiguez.aixinainventory.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    public List<Employee> findAllEmployees();
    public Optional<Employee> findEmployeeById(Long employeeId);
    public Employee addEmployee(Employee employee);
    public Employee updateEmployee(Employee employeeDetails, Employee employee);
    public void deleteEmployee (Employee employee);

}
