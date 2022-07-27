package com.raju.demo.sample.repository;

import com.raju.demo.sample.entity.InstructorDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorDetailsRepository extends JpaRepository<InstructorDetails,Integer> {
}
