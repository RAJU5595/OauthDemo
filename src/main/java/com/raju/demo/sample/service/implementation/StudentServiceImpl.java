package com.raju.demo.sample.service.implementation;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.raju.demo.sample.entity.Course;
import com.raju.demo.sample.entity.Student;
import com.raju.demo.sample.repository.CourseRepository;
import com.raju.demo.sample.repository.StudentRepository;
import com.raju.demo.sample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "student_service")
@PropertySource("classpath:messeges.properties")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Value("student_exist_error_msg")
    private String studentExistErrorMsg;

    @Value("student_not_exist_error_msg")
    private String studentNotExistErrorMsg;


    @Override
    public Student saveStudent(ObjectNode jsonObject) throws Exception {

        Student student = new Student();
        student.setName(jsonObject.get("name").asText());
        Set<String> courses = new HashSet<>();
        jsonObject.get("courses").forEach(JsonNode -> courses.add(JsonNode.asText()));

        for (String courseName : courses) {
            if (!Objects.equals(courseName, "")) {
                Course existedCourse = courseRepository.findCourseByName(courseName);
                if (existedCourse == null) {
                    Course course = new Course();
                    course.setName(courseName);
                    student.getCourses().add(course);
                } else {
                    student.getCourses().add(existedCourse);
                }
            }
        }

        Student existedStudent = studentRepository.findStudentByName(student.getName());
        Student newStudent;

        if (existedStudent == null) {
            newStudent = studentRepository.save(student);
        } else {
            throw new Exception(studentExistErrorMsg);
        }

        return newStudent;
    }

    @Override
    public Student getStudentDetails(String studentId) throws Exception {

        Optional<Student> result = studentRepository.findById(studentId);
        Student student;

        if (result.isPresent()) {
            student = result.get();
        } else {
            throw new Exception(studentNotExistErrorMsg + studentId);
        }

        return student;
    }

    @Override
    public Student updateStudent(String studentId, ObjectNode jsonObject) throws Exception {
        Student existedStudent = this.getStudentDetails(studentId);
        existedStudent.setName(jsonObject.get("name").asText());
        Set<String> courses = new HashSet<>();
        jsonObject.get("courses").forEach(JsonNode -> courses.add(JsonNode.asText()));

        existedStudent.getCourses().clear();
        for (String courseName : courses) {
            if (!Objects.equals(courseName, "")) {
                Course existedCourse = courseRepository.findCourseByName(courseName);
                if (existedCourse == null) {
                    Course course = new Course();
                    course.setName(courseName);
                    existedStudent.getCourses().add(course);
                }
                else{
                    existedStudent.getCourses().add(existedCourse);
                }
            }
        }

        return studentRepository.save(existedStudent);
    }

    @Override
    public void deleteStudent(String studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public List<Student> getAllTheStudentDetails() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getAllStudentsWhoEnrolledInMoreThanGivenNoOfCourses(Integer count) {
        return studentRepository.findAllStudentsWhoEnrolledInMoreThanGivenNoOfCourses(count);
    }
}

