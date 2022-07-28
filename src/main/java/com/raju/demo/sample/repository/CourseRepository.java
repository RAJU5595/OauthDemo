package com.raju.demo.sample.repository;

import com.raju.demo.sample.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,String> {
    Course findCourseByName(String courseName);
}
