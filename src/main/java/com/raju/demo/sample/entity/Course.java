package com.raju.demo.sample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.raju.demo.sample.idgenerator.CustomIdGenerator;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@Table(name="course")
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @GenericGenerator(
            name = "course_seq",
            strategy = "com.raju.demo.sample.idgenerator.CustomIdGenerator",
            parameters = {
                    @Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "COU_"),
                    @Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

    private String id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {DETACH, MERGE, PERSIST, REFRESH})
    @JoinTable(name="student_course",
            joinColumns = {@JoinColumn(name="course_id")},
            inverseJoinColumns = {@JoinColumn(name="student_id")}
    )

    @JsonIgnoreProperties(value = "courses")
    private List<Student> students=new ArrayList<>();

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
