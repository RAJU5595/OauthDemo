package com.raju.demo.sample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.raju.demo.sample.idgenerator.CustomIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.*;

@Entity
@Table(name="student")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @GenericGenerator(
            name = "student_seq",
            strategy = "com.raju.demo.sample.idgenerator.CustomIdGenerator",
            parameters = {
                    @Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "STU_"),
                    @Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

    private String id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {DETACH, MERGE, PERSIST, REFRESH})
    @JoinTable(name="student_course",
            joinColumns = {@JoinColumn(name="student_id")},
            inverseJoinColumns = {@JoinColumn(name="course_id")}
    )
    @JsonIgnoreProperties(value = "students")
    private List<Course> courses = new ArrayList<>();

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
}