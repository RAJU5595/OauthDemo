package com.raju.demo.sample.repository;

import com.raju.demo.sample.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,String> {
    Student findStudentByName(String studentName);

    @Query(value = "select id, name from \n" +
            "(\n" +
            "\tselect student_id,count(student_id) from student_course group by student_id having count(student_id)> :courseLimit\n" +
            ") \n" +
            "\tAs A inner join student on student_id = id ",nativeQuery = true)
    List<Student> findAllStudentsWhoEnrolledInMoreThanGivenNoOfCourses(@Param("courseLimit") Integer count);

}
