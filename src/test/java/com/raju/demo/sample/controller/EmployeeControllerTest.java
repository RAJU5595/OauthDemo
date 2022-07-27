package com.raju.demo.sample.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raju.demo.sample.entity.Employee;
import com.raju.demo.sample.service.implementation.EmployeeServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@WebMvcTest(value = EmployeeController.class)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @MockBean
    private EmployeeServiceImp employeeServiceImp;

    @Test
    @WithMockUser(roles = {"admin","user"})
    void getEmployee() throws Exception {
        Employee employee =  new Employee(25,"ramana",40000);
        when(employeeServiceImp.getEmployee(25)).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/employees/25");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //CustomErrorResponse resultEmployee = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<CustomErrorResponse>(){});
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(roles = {"admin","user"})
    void getEmployee2() throws Exception {
        Employee employee =  new Employee(45,"ramana",40000);
        when(employeeServiceImp.getEmployee(46)).thenThrow(RuntimeException.class);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/employees/46");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertThrows(RuntimeException.class,()->{
            employeeServiceImp.getEmployee(46);
        });
    }

    @Test
    @WithMockUser(roles = {"admin"})
    void getEmployees1() throws Exception {
        List<Employee> value = new ArrayList<>(Stream.of(new Employee(55,"raju",50000),
                new Employee(25,"ramana",40000),
                new Employee(35,"kalyan",30000),
                new Employee(45,"rajesh",60000)
        ).collect(Collectors.toList()));
        when(employeeServiceImp.getAllEmployees()).thenReturn(value);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/employees");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        List<Employee> employeeList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Employee>>(){});
        assertEquals(4,employeeList.size());
    }

    @Test
    @WithMockUser(roles = {"user"})
    void getEmployees2() throws Exception {
        List<Employee> value = new ArrayList<>(Stream.of(new Employee(55,"raju",50000),
                new Employee(25,"ramana",40000),
                new Employee(35,"kalyan",30000),
                new Employee(45,"rajesh",60000)
        ).collect(Collectors.toList()));
        when(employeeServiceImp.getAllEmployees()).thenReturn(value);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/employees");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //CustomErrorResponse error = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<CustomErrorResponse>(){});
        assertEquals(result.getResponse().getStatus(),400);
    }

    @Test
    @WithMockUser(roles = {"admin"})
    void saveEmployee() throws Exception {
        Employee employee = new Employee(88,"John",66666);
        String requestBody = "{\"id\":\"88\",\"name\":\"John\",\"salary\":\"66666\"}";
        when(employeeServiceImp.saveEmployee(
                Mockito.any(Employee.class))).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/employees")
                .accept(MediaType.APPLICATION_JSON).content(requestBody)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(result.getResponse().getStatus(),201);
    }

    @Test
    @WithMockUser(roles = {"admin"})
    void updateEmployee() throws Exception {
        Employee oldEmployee = new Employee(33,"Ashok",56777);
        String requestBody = "{\"id\":\"88\",\"name\":\"John\",\"salary\":\"66666\"}";
        when(employeeServiceImp.getEmployee(33)).thenReturn(oldEmployee);
        oldEmployee.setName("John");
        oldEmployee.setSalary(66666);
        when(employeeServiceImp.saveEmployee(oldEmployee)).thenReturn(oldEmployee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/employees/33")
                .accept(MediaType.APPLICATION_JSON).content(requestBody)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(result.getResponse().getStatus(),200);
    }

    @Test
    @WithMockUser(roles = {"admin"})
    void deleteEmployee() throws Exception {
        willDoNothing().given(employeeServiceImp).deleteEmployee(33);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/employees/33");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(result.getResponse().getStatus(),200);
    }
}