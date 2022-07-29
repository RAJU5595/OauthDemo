package com.raju.demo.sample.repository;

import com.raju.demo.sample.entity.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BacklogRepository extends JpaRepository<Backlog,String> {
    Backlog findSubjectByName(String subjectName);

    @Query(value = "select * from backlog where student_id=?1",nativeQuery = true)
    List<Backlog> findAllBacklogs(String studentId);
}
