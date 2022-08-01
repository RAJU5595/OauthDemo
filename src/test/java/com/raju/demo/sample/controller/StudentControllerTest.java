package com.raju.demo.sample.controller;

import com.raju.demo.sample.entity.Student;
import com.raju.demo.sample.service.StudentService;
import com.raju.demo.sample.service.implementation.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @MockBean
    @Qualifier("student_service")
    private StudentService studentService;

    @Test
    void saveStudent() {
    }

    @Test
    @WithMockUser(roles = {"admin","user"})
    void getStudentDetails() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/student/STU_00001");
        Student student = new Student("1","a");
        Mockito.when(studentService.getStudentDetails("STU_00001")).thenReturn(student);
        MvcResult result = (MvcResult) mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    @WithMockUser(roles = {"admin","user"})
    void testPathVariableConstraintOfGetStudentDetails() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/student/1");
        MvcResult result = (MvcResult) mockMvc.perform(requestBuilder).andReturn();
        assertEquals(400,result.getResponse().getStatus());
    }

    @Test
    void updateStudent() {
    }

    @Test
    @WithMockUser(roles = {"admin"})
    void deleteStudent() throws Exception {
        willDoNothing().given(studentService).deleteStudent("STU_0001");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/student/STU_00001");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(result.getResponse().getStatus(),200);
    }

    @Test
    @WithMockUser(roles = {"admin"})
    void getAllStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(new Student("1","a"));
        students.add(new Student("2","b"));
        when(studentService.getAllTheStudentDetails()).thenReturn(students);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/student");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    @WithMockUser(roles = {"admin"})
    void getAllStudentsWhoEnrolledInMoreThanGivenNoOfCoursesWithQueryParam() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(new Student("1","a"));
        students.add(new Student("2","b"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/student").queryParam("courseLimit","1");
        when(studentService.getAllStudentsWhoEnrolledInMoreThanGivenNoOfCourses(1)).thenReturn(students);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    @WithMockUser(roles = {"admin"})
    void getAllStudentsWhoEnrolledInMoreThanGivenNoOfCoursesWithoutQueryParam() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(new Student("3","c"));
        students.add(new Student("4","d"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/student");
        when(studentService.getAllTheStudentDetails()).thenReturn(students);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }
}