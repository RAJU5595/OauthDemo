package com.raju.demo.sample.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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

    @Autowired
    private StudentServiceImpl studentService;

    @Test
    void saveStudent() throws Exception {
    }

    @Test
    void getStudentDetails() throws Exception {
        Student student = new Student("1","name");
        when(studentRepository.findById("1")).thenReturn(Optional.of(student));
        assertEquals(student,studentService.getStudentDetails("1"));
    }

    @Test
    void test_getStudentDetails_studentNotFoundException(){
        Student student = new Student("1","name");
        when(studentRepository.findById("1")).thenReturn(Optional.of(student));
        assertThrows(Exception.class,()->{
            studentService.getStudentDetails("2");
        });
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
        Student student = new Student("1","name");
        studentService.deleteStudent(student.getId());
        verify(studentRepository,times(1)).deleteById(student.getId());
    }

    @Test
    void getAllStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("1","a"));
        students.add(new Student("2","b"));
        when(studentRepository.findAll()).thenReturn(students);
        assertEquals(students,studentService.getAllTheStudentDetails());
    }
}