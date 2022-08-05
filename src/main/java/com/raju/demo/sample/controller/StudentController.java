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

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/students")
@Validated
public class StudentController {

    @Autowired
    @Qualifier("student_service")
    private StudentService studentService;

    @PostMapping()
    @RolesAllowed({"admin"})
    public ResponseEntity<?> saveStudent(@RequestBody ObjectNode jsonObject) throws Exception {
        Student student = studentService.saveStudent(jsonObject);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student details saved successfully with id :"+student.getId());
    }

    @GetMapping("/{studentId}")
    @RolesAllowed({"user","admin"})
    public Student getStudentDetails(@PathVariable @Pattern(regexp = "^STU_\\d{5}$") String studentId) throws Exception {
        return studentService.getStudentDetails(studentId);
    }

    @PutMapping("/{studentId}")
    @RolesAllowed({"admin"})
    public ResponseEntity<?> updateStudent(@PathVariable @Pattern(regexp = "^STU_\\d{5}$") String studentId,@RequestBody ObjectNode jsonObject) throws Exception {
        Student student = studentService.updateStudent(studentId,jsonObject);
        return ResponseEntity.status(HttpStatus.OK).body("Student details updates successfully with id :"+student.getId());
    }

    @DeleteMapping("/{studentId}")
    @RolesAllowed({"admin"})
    public ResponseEntity<?> deleteStudent(@PathVariable @Pattern(regexp = "^STU_\\d{5}$") String studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.status(HttpStatus.OK).body("Student details deletes successfully with id :"+studentId);
    }

    @GetMapping()
    @RolesAllowed({"admin"})
    public List<Student> getAllStudentsWhoEnrolledInMoreThanGivenNoOfCourses(@RequestParam(value = "courseLimit",required = false) Integer courseLimit){
        if(courseLimit==null){
            return studentService.getAllTheStudentDetails();
        }
        return studentService.getAllStudentsWhoEnrolledInMoreThanGivenNoOfCourses(courseLimit);
    }

}
