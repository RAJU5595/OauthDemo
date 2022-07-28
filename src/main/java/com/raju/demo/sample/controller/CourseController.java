package com.raju.demo.sample.controller;

import com.raju.demo.sample.entity.Course;
import com.raju.demo.sample.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    @Qualifier("course_service")
    private CourseService courseService;

    @GetMapping("/{courseId}/students")
    public List<String> getAllTheStudentsOfTheCourse(@PathVariable String courseId) throws Exception {
        List<String> registeredStudents = courseService.getAllStudentsOfTheCourse(courseId);
        return registeredStudents;
    }

    @GetMapping("/{courseId}")
    public Course getTheCourseDetails(@PathVariable String courseId) throws Exception {
        Course course = courseService.getCourseDetails(courseId);
        return course;
    }
}
