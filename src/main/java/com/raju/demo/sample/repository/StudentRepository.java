package com.raju.demo.sample.repository;

import com.raju.demo.sample.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface StudentRepository extends JpaRepository<Student,String> {
    Student findStudentByName(String studentName);

}
