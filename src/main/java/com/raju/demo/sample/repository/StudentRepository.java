package com.raju.demo.sample.repository;

import com.raju.demo.sample.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
    Student findStudentByName(String studentName);
}
