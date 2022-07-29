package com.raju.demo.sample.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.raju.demo.sample.entity.Student;
import com.raju.demo.sample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@Validated
public class StudentController {

    @Autowired
    @Qualifier("student_service")
    private StudentService studentService;

    @PostMapping()
    public ResponseEntity<?> saveStudent(@RequestBody ObjectNode jsonObject) throws Exception {
        Student student = studentService.saveStudent(jsonObject);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student details saved successfully with id :"+student.getId());
    }

    @GetMapping("/{studentId}")
    public Student getStudentDetails(@PathVariable @Size(max=9) String studentId) throws Exception {
        Student student = studentService.getStudentDetails(studentId);
        return student;
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable String studentId,@RequestBody ObjectNode jsonObject) throws Exception {
        Student student = studentService.updateStudent(studentId,jsonObject);
        return ResponseEntity.status(HttpStatus.OK).body("Student details updates successfully with id :"+student.getId());
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable String studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.status(HttpStatus.OK).body("Student details updates successfully with id :"+studentId);
    }
}
