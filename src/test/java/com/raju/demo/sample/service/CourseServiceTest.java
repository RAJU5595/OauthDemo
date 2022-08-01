package com.raju.demo.sample.service;

import com.raju.demo.sample.entity.Course;
import com.raju.demo.sample.entity.Student;
import com.raju.demo.sample.repository.CourseRepository;
import com.raju.demo.sample.service.implementation.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseServiceImpl courseService;

    @MockBean
    private CourseRepository courseRepository;

    @Test
    void getCourseDetails() throws Exception {
        Course course = new Course("1","maths");
        when(courseRepository.findById("1")).thenReturn(Optional.of(course));
        assertEquals(course,courseService.getCourseDetails("1"));
    }

    @Test
    void test_getCouseDetails_courseNotFoundException(){
        Course course = new Course("1","maths");
        when(courseRepository.findById("1")).thenReturn(Optional.of(course));
        assertThrows(Exception.class,()->{
            courseService.getCourseDetails("2");
        });
    }

    @Test
    void getAllCourses(){
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("1","maths"));
        when(courseRepository.findAll()).thenReturn(courses);
        assertEquals(courses,courseService.getAllTheCourses());
    }

}