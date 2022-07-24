package com.raju.demo.sample.service;

import com.raju.demo.sample.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee getEmployee(int employeeId);

    public List<Employee> getAllEmployees();

    public Employee saveEmployee(Employee employee);

    public void deleteEmployee(Integer employeeId);

}
