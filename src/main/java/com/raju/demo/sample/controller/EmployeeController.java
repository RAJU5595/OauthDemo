package com.raju.demo.sample.controller;

import com.raju.demo.sample.entity.Employee;
import com.raju.demo.sample.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    @RolesAllowed("user")
    public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId){
        return ResponseEntity.ok(employeeService.getEmployee(employeeId));
    }

    @GetMapping()
    @RolesAllowed("admin")
    public ResponseEntity<List<Employee>> getEmployee(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

}
