package com.raju.demo.sample.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.raju.demo.sample.entity.Student;

import java.util.List;

public interface StudentService {

    public Student saveStudent(ObjectNode jsonObject) throws Exception;

    public Student getStudentDetails(String studentId) throws Exception;

    public Student updateStudent(String studentId, ObjectNode jsonObject) throws Exception;

    public void deleteStudent(String studentId);
}
