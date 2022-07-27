package com.raju.demo.sample.entity;

import com.raju.demo.sample.idgenerator.CustomIdGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;


@Entity
@Table(name="instructor_detail")
@Data
public class InstructorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_details_seq")
    @GenericGenerator(
            name = "instructor_details_seq",
            strategy = "com.raju.demo.sample.idgenerator.CustomIdGenerator",
            parameters = {
                    @Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "ID_"),
                    @Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

    private String id;

    private String channel;

    private String hobby;
}
