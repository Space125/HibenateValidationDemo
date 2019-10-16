package com.example.validation.hibenatevalidationdemo.models;

import com.example.validation.hibenatevalidationdemo.utils.New;
import com.example.validation.hibenatevalidationdemo.utils.Update;
import com.example.validation.hibenatevalidationdemo.validations.DateValidator;
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

    @Null(groups = {New.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(groups = {New.class})
    @NotEmpty(groups = {New.class})
    @Size(min = 1, max = 50, groups = {New.class, Update.class})
    @Column(name = "name")
    private String name;

    @NotNull(groups = {New.class})
    @NotEmpty(groups = {New.class})
    @Size(min = 1, max = 50, groups = {New.class, Update.class})
    @Column(name = "country")
    private String country;

    @NotEmpty(groups = {New.class})
    @Column(name = "airplaneType")
    @Enumerated(EnumType.STRING)
    //@EnumValidator(enumClass = AirplaneType.class, groups = {New.class, Update.class})
    private AirplaneType airplaneType;
    //private String airplaneType;

    @NotNull(groups = {New.class})
    @Column(name = "prodDate")
    @DateValidator(groups = {New.class, Update.class})
    private Date prodDate;

    @Column(name = "isUsed")
    @JsonProperty
    private boolean isUsed;

    @NotNull(groups = {New.class})
    @DecimalMin(value = "0.01", groups = {New.class, Update.class})
    @DecimalMax(value = "0.99", groups = {New.class, Update.class})
    @Column(name = "speed")
    private Double speed;

    @NotNull(groups = {New.class})
    @Min(value = 1, groups = {New.class, Update.class})
    @Max(value = 9999, groups = {New.class, Update.class})
    @Column(name = "crewSize")
    private Integer crewSize;


    @Column(name = "rating")
    private Double rating;

}
