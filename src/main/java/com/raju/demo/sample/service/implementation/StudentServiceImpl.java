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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

        List<String> backlogs = new ArrayList<>();
        jsonObject.get("backlogs").forEach(JsonNode -> backlogs.add(JsonNode.asText()));

        List<String> courses = new ArrayList<>();
        jsonObject.get("courses").forEach(JsonNode -> courses.add(JsonNode.asText()));

        for (String subjectName : backlogs) {
            if (!Objects.equals(subjectName, "")) {
                Backlog backlog = new Backlog();
                backlog.setName(subjectName);
                student.getBacklogs().add(backlog);
            }
        }

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
        existedStudent.setId(studentId);
        existedStudent.setName(jsonObject.get("name").asText());
        existedStudent.getBacklogs().clear();
        existedStudent.getCourses().clear();

        List<String> backlogs = new ArrayList<>();
        jsonObject.get("backlogs").forEach(JsonNode -> backlogs.add(JsonNode.asText()));

        List<String> courses = new ArrayList<>();
        jsonObject.get("courses").forEach(JsonNode -> courses.add(JsonNode.asText()));

        for (String subjectName : backlogs) {
            Backlog backlog = new Backlog();
            backlog.setName(subjectName);
            existedStudent.getBacklogs().add(backlog);
        }

        for (String courseName : courses) {
            if (!Objects.equals(courseName, "")) {
                Course existedCourse = courseRepository.findCourseByName(courseName);
                if (existedCourse == null) {
                    Course course = new Course();
                    course.setName(courseName);
                    existedStudent.getCourses().add(course);
                }
            }
        }

        return studentRepository.save(existedStudent);
    }
}

