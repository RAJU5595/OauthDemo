package com.raju.demo.sample.controller;

import com.raju.demo.sample.entity.Course;
import com.raju.demo.sample.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("course_service")
    private CourseService courseService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @WithMockUser(roles = {"admin","user"})
    void getTheCourseDetails() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/course/COU_00001");
        Course course = new Course("1","maths");
        when(courseService.getCourseDetails("COU_00001")).thenReturn(course);
        MvcResult result = (MvcResult) mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    @WithMockUser(roles = {"admin","user"})
    void testPathVariableConstraintOfGetCourseDetails() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/course/5");
        MvcResult result = (MvcResult) mockMvc.perform(requestBuilder).andReturn();
        assertEquals(400,result.getResponse().getStatus());
    }

    @Test
    @WithMockUser(roles = {"admin"})
    void getAllTheCourseDetails() throws Exception {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("1","a"));
        courses.add(new Course("2","b"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/course");
        MvcResult result = (MvcResult) mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }
}