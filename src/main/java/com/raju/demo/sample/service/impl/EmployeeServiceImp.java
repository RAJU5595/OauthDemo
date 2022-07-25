package com.raju.demo.sample.service.impl;

import com.raju.demo.sample.entity.Employee;
import com.raju.demo.sample.repository.EmployeeRepository;
import com.raju.demo.sample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    public void initializeEmployeeTable(){
        employeeRepository.saveAll(Stream.of(new Employee("raju",50000),
                new Employee("ramana",40000),
                new Employee("kalyan",30000),
                new Employee("rajesh",60000),
                new Employee("shyam",70000)
                ).collect(Collectors.toList()));
    }

    public Employee getEmployee(int employeeId){
        Optional<Employee> result = employeeRepository.findById(employeeId);
        Employee employee = null;
        if(result.isPresent()){
            employee = result.get();
        }
        else{
            throw new RuntimeException("Did not find employee with id :"+employeeId);
        }
        return  employee;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer employeeId){
        employeeRepository.deleteById(employeeId);
    }
}