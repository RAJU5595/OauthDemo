package com.raju.demo.sample.entity;

import com.raju.demo.sample.idgenerator.CustomIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;


@Entity
@Table(name="backlog")
@Setter
@NoArgsConstructor
@Getter
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "backlog_seq")
    @GenericGenerator(
            name = "backlog_seq",
            strategy = "com.raju.demo.sample.idgenerator.CustomIdGenerator",
            parameters = {
                    @Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "SUB_"),
                    @Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

    private String id;

    private String name;

    public Backlog(String name) {
        this.name = name;
    }
}
