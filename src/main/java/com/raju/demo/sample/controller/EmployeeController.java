package com.raju.demo.sample.controller;

import com.raju.demo.sample.entity.Employee;
import com.raju.demo.sample.service.impl.EmployeeServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImp employeeServiceImp;

    @GetMapping("/{employeeId}")
    @RolesAllowed({"user","admin"})
    public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId){
        return ResponseEntity.ok(employeeServiceImp.getEmployee(employeeId));
    }

    @GetMapping()
    @RolesAllowed("admin")
    public ResponseEntity<List<Employee>> getEmployees(){
        return ResponseEntity.ok(employeeServiceImp.getAllEmployees());
    }

    @PostMapping()
    @RolesAllowed("admin")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee newEmployee){
        Employee employee = employeeServiceImp.saveEmployee(newEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee Created Successfully with id :"+employee.getId());
    }

    @PutMapping("/{employeeId}")
    @RolesAllowed("admin")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer employeeId,@RequestBody Employee updatedEmployee){
        Employee oldEmployee = employeeServiceImp.getEmployee(employeeId);
        oldEmployee.setName(updatedEmployee.getName());
        oldEmployee.setSalary(updatedEmployee.getSalary());
        employeeServiceImp.saveEmployee(oldEmployee);
        return ResponseEntity.status(HttpStatus.OK).body("Employee with "+oldEmployee.getId()+" Data Updated Successfully");
    }

    @DeleteMapping("/{employeeId}")
    @RolesAllowed("admin")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer employeeId){
        employeeServiceImp.deleteEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body("Employee Data Deleted Successfully");
    }

}
