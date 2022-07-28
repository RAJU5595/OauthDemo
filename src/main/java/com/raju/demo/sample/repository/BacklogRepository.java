package com.raju.demo.sample.repository;

import com.raju.demo.sample.entity.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BacklogRepository extends JpaRepository<Backlog,String> {
    Backlog findSubjectByName(String subjectName);
}
