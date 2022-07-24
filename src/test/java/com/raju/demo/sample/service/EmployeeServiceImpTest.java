package com.raju.demo.sample.service;

import com.raju.demo.sample.entity.Employee;
import com.raju.demo.sample.repository.EmployeeRepository;
import com.raju.demo.sample.service.impl.EmployeeServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceImpTest {
    @MockBean
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeServiceImp employeeServiceImp;

    @Test
    void getEmployee() {
        Employee employee = new Employee(67,"amit",67222);
        when(employeeRepository.findById(67)).thenReturn(Optional.of(employee));
        assertEquals(employee, employeeServiceImp.getEmployee(67));
    }

    @Test
    void testGetEmployee_EmployeeNotFoundException(){
        Employee employee = new Employee(86,"sharath",56666);
        when(employeeRepository.findById(86)).thenReturn(Optional.of(employee));
        assertThrows(RuntimeException.class,()->{
            employeeServiceImp.getEmployee(82);
        });
    }

    @Test
    void getAllEmployeesTest1() {
        List<Employee> employeeList = new ArrayList<>(Stream.of(new Employee(55,"raju",50000),
                new Employee(25,"ramana",40000),
                new Employee(35,"kalyan",30000),
                new Employee(45,"rajesh",60000)
        ).collect(Collectors.toList()));
        when(employeeRepository.findAll()).thenReturn(employeeList);
        assertEquals(4, employeeServiceImp.getAllEmployees().size());
    }

    @Test
    void getAllEmployeesTest2() {
        List<Employee> employeeList = new ArrayList<>(Stream.of(new Employee(1,"raju",50000),
                new Employee(2,"ramana",40000),
                new Employee(3,"kalyan",30000),
                new Employee(4,"rajesh",60000)
        ).collect(Collectors.toList()));
        when(employeeRepository.findAll()).thenReturn(employeeList);
        assertNotEquals(2, employeeServiceImp.getAllEmployees().size());
    }

    @Test
    void saveEmployee() {
        Employee employee = new Employee(65,"Suresh",67000);
        when(employeeRepository.save(employee)).thenReturn(employee);
        assertEquals(employee, employeeServiceImp.saveEmployee(employee));
    }

    @Test
    void deleteEmployee() {
        Employee employee = new Employee(65,"Suresh",67000);
        employeeServiceImp.deleteEmployee(employee.getId());
        verify(employeeRepository,times(1)).deleteById(employee.getId());
    }

}