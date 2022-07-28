package com.raju.demo.sample.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.raju.demo.sample.entity.Student;
import com.raju.demo.sample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    @Qualifier("student_service")
    private StudentService studentService;

    @PostMapping()
    public ResponseEntity<?> saveStudent(@RequestBody ObjectNode jsonObject) throws Exception {
        System.out.println(jsonObject);
        Student student = studentService.saveStudent(jsonObject);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student details saved successfully with id :"+student.getId());
    }

    @GetMapping("/{studentId}")
    public Student getStudentDetails(@PathVariable String studentId) throws Exception {
        Student student = studentService.getStudentDetails(studentId);
        return student;
    }
}
