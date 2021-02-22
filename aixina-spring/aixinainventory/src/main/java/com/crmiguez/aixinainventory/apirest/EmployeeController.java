package com.crmiguez.aixinainventory.apirest;

import com.crmiguez.aixinainventory.entities.Employee;
import com.crmiguez.aixinainventory.exception.ResourceNotFoundException;
import com.crmiguez.aixinainventory.service.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/api_aixina/v1")
@RequestMapping("/api_aixina/v1/employeemanage")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class EmployeeController {

    @Autowired
    @Qualifier("employeeService")
    private IEmployeeService employeeService;

    @PreAuthorize("hasAuthority('PERM_READ_ALL_EMPLOYEES')")
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @PreAuthorize("hasAuthority('PERM_READ_EMPLOYEE')")
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeService.findEmployeeById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found on :: "+ employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @PreAuthorize("hasAuthority('PERM_CREATE_EMPLOYEE')")
    @PostMapping("/employee")
    public Employee createEmployee(@Valid @RequestBody Employee employee) { return employeeService.addEmployee(employee); }

    @PreAuthorize("hasAuthority('PERM_UPDATE_EMPLOYEE')")
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable(value = "id") Long employeeId,
            @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeService.findEmployeeById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found on :: "+ employeeId));

        final Employee updatedEmployee = employeeService.updateEmployee(employeeDetails, employee);
        if  (updatedEmployee == null){
            return new ResponseEntity<Employee>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return ResponseEntity.ok(updatedEmployee);
        }
    }

    @PreAuthorize("hasAuthority('PERM_DELETE_EMPLOYEE')")
    @DeleteMapping("/employee/{id}")
    public Map<String, Boolean> deleteEmployee(
            @PathVariable(value = "id") Long employeeId) throws Exception {
        Employee employee = employeeService.findEmployeeById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found on :: "+ employeeId));

        employeeService.deleteEmployee(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
