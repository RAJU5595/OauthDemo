package com.raju.demo.sample.controller;

import com.raju.demo.sample.entity.Course;
import com.raju.demo.sample.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/course")
@Validated
public class CourseController {

    @Autowired
    @Qualifier("course_service")
    private CourseService courseService;

    @GetMapping("/{courseId}")
    @RolesAllowed({"user","admin"})
    public Course getTheCourseDetails(@PathVariable @Pattern(regexp = "^COU_\\d{5}$") String courseId) throws Exception {
        return courseService.getCourseDetails(courseId);
    }

    @GetMapping()
    @RolesAllowed({"admin"})
    public List<Course> getAllTheCourseDetails(){
        return courseService.getAllTheCourses();
    }
}
