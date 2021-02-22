package com.crmiguez.aixinainventory.apirest;

import com.crmiguez.aixinainventory.entities.Department;
import com.crmiguez.aixinainventory.exception.ResourceNotFoundException;
import com.crmiguez.aixinainventory.service.department.IDepartmentService;
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
@RequestMapping("/api_aixina/v1/departmentmanage")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class DepartmentController {

    @Autowired
    @Qualifier("departmentService")
    private IDepartmentService departmentService;

    @PreAuthorize("hasAuthority('PERM_READ_ALL_DEPARTMENTS')")
    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentService.findAllDepartments();
    }

    @PreAuthorize("hasAuthority('PERM_READ_DEPARTMENT')")
    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(
            @PathVariable(value = "id") String departmentId) throws ResourceNotFoundException {
        Department department = departmentService.findDepartmentById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found on :: "+ departmentId));
        return ResponseEntity.ok().body(department);
    }

    @PreAuthorize("hasAuthority('PERM_CREATE_DEPARTMENT')")
    @PostMapping("/department")
    public Department createDepartment(@Valid @RequestBody Department department) { return departmentService.addDepartment(department); }

    @PreAuthorize("hasAuthority('PERM_UPDATE_DEPARTMENT')")
    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable(value = "id") String departmentId,
            @Valid @RequestBody Department departmentDetails) throws ResourceNotFoundException {
        Department department = departmentService.findDepartmentById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found on :: "+ departmentId));

        final Department updatedDepartment = departmentService.updateDepartment(departmentDetails, department);
        if  (updatedDepartment == null){
            return new ResponseEntity<Department>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return ResponseEntity.ok(updatedDepartment);
        }
    }

    @PreAuthorize("hasAuthority('PERM_DELETE_DEPARTMENT')")
    @DeleteMapping("/department/{id}")
    public Map<String, Boolean> deleteDepartment(
            @PathVariable(value = "id") String departmentId) throws Exception {
        Department department = departmentService.findDepartmentById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found on :: "+ departmentId));

        departmentService.deleteDepartment(department);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
