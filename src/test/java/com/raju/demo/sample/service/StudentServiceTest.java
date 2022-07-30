package com.raju.demo.sample.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.raju.demo.sample.entity.Course;
import com.raju.demo.sample.entity.Student;
import com.raju.demo.sample.repository.CourseRepository;
import com.raju.demo.sample.repository.StudentRepository;
import com.raju.demo.sample.service.implementation.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class StudentServiceTest {

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void saveStudent() throws Exception {
        ObjectNode jsonObject = objectMapper.createObjectNode();
        jsonObject.put("name","raju");
        jsonObject.put("courses","frontend,backend");
        jsonObject.put("backlogs","maths,physics");
        Student student = new Student("1","name");
        Set<String> courses = new HashSet<>();
        when(studentRepository.save(student)).thenReturn(student);
        when(studentRepository.findStudentByName(student.getName())).thenReturn(student);
        when(courseRepository.findCourseByName("")).thenReturn(new Course());
        assertEquals(student,studentService.saveStudent(jsonObject));
    }

    @Test
    void getStudentDetails() {
    }

    @Test
    void getBacklogs() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
    }
}