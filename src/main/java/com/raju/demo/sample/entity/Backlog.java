package com.raju.demo.sample.entity;

import com.raju.demo.sample.idgenerator.CustomIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="backlog")
@Setter
@NoArgsConstructor
@Getter
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Backlog(String name) {
        this.name = name;
    }

    public Backlog(int id, String subject) {
    }
}
