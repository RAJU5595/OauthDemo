package com.raju.demo.sample.service;

import com.raju.demo.sample.entity.Course;
import com.raju.demo.sample.entity.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    public Course getCourseDetails(String courseId) throws Exception;

    public List<Course> getAllTheCourses();

}
