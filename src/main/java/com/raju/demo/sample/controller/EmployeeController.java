package com.raju.demo.sample.controller;

import com.raju.demo.sample.entity.Employee;
import com.raju.demo.sample.service.EmployeeService;

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
    private EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    @RolesAllowed({"user","admin"})
    public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId){
        return ResponseEntity.ok(employeeService.getEmployee(employeeId));
    }

    @GetMapping()
    @RolesAllowed("admin")
    public ResponseEntity<List<Employee>> getEmployee(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping()
    @RolesAllowed("admin")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee newEmployee){
        employeeService.saveEmployee(newEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee Created Successfully");
    }

    @PutMapping("/{employeeId}")
    @RolesAllowed("admin")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer employeeId,@RequestBody Employee updatedEmployee){
        Employee oldEmployee = employeeService.getEmployee(employeeId);
        oldEmployee.setName(updatedEmployee.getName());
        oldEmployee.setSalary(updatedEmployee.getSalary());
        employeeService.saveEmployee(oldEmployee);
        return ResponseEntity.status(HttpStatus.OK).body("Employee Data Updated Successfully");
    }

    @DeleteMapping("/{employeeId}")
    @RolesAllowed("admin")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body("Employee Data Deleted Successfully");
    }

}
