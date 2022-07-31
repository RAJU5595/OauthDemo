package com.raju.demo.sample.service.implementation;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.raju.demo.sample.entity.Backlog;
import com.raju.demo.sample.entity.Course;
import com.raju.demo.sample.entity.Student;
import com.raju.demo.sample.repository.BacklogRepository;
import com.raju.demo.sample.repository.CourseRepository;
import com.raju.demo.sample.repository.StudentRepository;
import com.raju.demo.sample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "student_service")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private BacklogRepository backlogRepository;

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
            throw new Exception("Student name already Existed in the Database");
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
            throw new Exception("There is no student with the given Id :" + studentId);
        }

        return student;
    }

    @Override
    public Student updateStudent(String studentId, ObjectNode jsonObject) throws Exception {
        Student existedStudent = this.getStudentDetails(studentId);
        existedStudent.setName(jsonObject.get("name").asText());
        Set<String> backlogs = new HashSet<>();
        jsonObject.get("backlogs").forEach(JsonNode -> backlogs.add(JsonNode.asText()));
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
}

