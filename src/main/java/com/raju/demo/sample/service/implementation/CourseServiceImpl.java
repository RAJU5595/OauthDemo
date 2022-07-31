package com.raju.demo.sample.service.implementation;

import com.raju.demo.sample.entity.Course;
import com.raju.demo.sample.repository.CourseRepository;
import com.raju.demo.sample.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service(value = "course_service")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course getCourseDetails(String courseId) throws Exception {
        Optional<Course> result = courseRepository.findById(courseId);

        Course course;
        if(result.isPresent()){
            course = result.get();
        }
        else{
            throw new Exception("There is no course with the given Id :"+courseId);
        }

        return course;
    }
}
