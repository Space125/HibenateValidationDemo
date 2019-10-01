package com.example.validation.hibenatevalidationdemo.models;

import com.example.validation.hibenatevalidationdemo.validations.EnumValidator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author Ivan Kurilov on 01.10.2019
 */

@Entity
@Table(name = "airplane")
@Data
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "country")
    private String country;

    @Column(name = "airplaneType")
    //@Enumerated(EnumType.STRING)
    @EnumValidator(enumClazz = AirplaneType.class)
    //private AirplaneType airplaneType;
    private String airplaneType;

    @NotNull
    @Column(name = "prodDate")
    private Date prodDate;

    @Column(name = "isUsed")
    @JsonProperty
    private boolean isUsed;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("0.99")
    @Column(name = "speed")
    private Double speed;

    @NotNull
    @Min(1)
    @Max(9999)
    @Column(name = "crewSize")
    private Integer crewSize;

    @Column(name = "rating")
    private Double rating;


}
