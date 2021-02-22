package com.crmiguez.aixinainventory.service.employee;

import com.crmiguez.aixinainventory.entities.Employee;
import com.crmiguez.aixinainventory.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("employeeService")
public class EmployeeService implements IEmployeeService {

    @Autowired
    @Qualifier("employeeRepository")
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findEmployeeById(Long employeeId){
        return employeeRepository.findById(employeeId);
    }

    @Override
    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee (Employee employee){
        employeeRepository.delete(employee);
    }

    @Override
    public Employee updateEmployee(Employee employeeDetails, Employee employee){

        employee.setEmployeeName(employeeDetails.getEmployeeName());
        employee.setEmployeeLastNames(employeeDetails.getEmployeeLastNames());
        employee.setEmployeeNIF(employeeDetails.getEmployeeNIF());
        employee.setEmployeeRegisterDate(employeeDetails.getEmployeeRegisterDate());
        employee.setEmployeeShutDate(employeeDetails.getEmployeeShutDate());
        employee.setItem(employeeDetails.getItem());
        employee.setItemSet(employeeDetails.getItemSet());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setLocation(employeeDetails.getLocation());

        return employeeRepository.save(employee);
    }
}
