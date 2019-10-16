package com.example.validation.hibenatevalidationdemo.controllers;

import com.example.validation.hibenatevalidationdemo.models.Airplane;
import com.example.validation.hibenatevalidationdemo.models.AirplaneType;
import com.example.validation.hibenatevalidationdemo.services.AirplaneService;
import com.example.validation.hibenatevalidationdemo.specifications.*;
import com.example.validation.hibenatevalidationdemo.utils.New;
import com.example.validation.hibenatevalidationdemo.utils.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * @author Ivan Kurilov on 01.10.2019
 */
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class AirplaneController {


    private final AirplaneService airplaneService;

    @GetMapping(value = "/airplanes")
    public ResponseEntity<List<Airplane>> getAllAirplane(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "airplaneType", required = false) AirplaneType airplaneType,
            @RequestParam(value = "after", required = false) Long after,
            @RequestParam(value = "before", required = false) Long before,
            @RequestParam(value = "isUsed", required = false) Boolean isUsed,
            @RequestParam(value = "minSpeed", required = false) Double minSpeed,
            @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
            @RequestParam(value = "minCrewSize", required = false) Double minCrewSize,
            @RequestParam(value = "maxCrewSize", required = false) Double maxCrewSize,
            @RequestParam(value = "minRating", required = false) Double minRating,
            @RequestParam(value = "maxRating", required = false) Double maxRating) {

        Specification specByName = new StringSpecification(
                new StringCriteria("name", ":", name));

        Specification specByCountry = new StringSpecification(
                new StringCriteria("country", ":", country));

        Specification specByAirplaneType = new StringSpecification(
                new StringCriteria("airplaneType", ":", airplaneType));

        Specification specDateBetween = null;
        if (after != null && before != null) {
            Date afterDate = new Date(after);
            Date beforeDate = new Date(before);
            specDateBetween = new DateBetweenSpecification(new DateBetweenCriteria(afterDate, beforeDate));
        }

        Specification specIsUsed = null;
        if (isUsed != null) {
            specIsUsed = new BooleanSpecification(new BooleanCriteria(isUsed));
        }

        Specification<Airplane> specSpeed = new DoubleBetweenSpecification(
                new DoubleBetweenCriteria("speed", minSpeed, maxSpeed));

        Specification<Airplane> specCrewSize = new DoubleBetweenSpecification(
                new DoubleBetweenCriteria("crewSize", minCrewSize, maxCrewSize));

        Specification<Airplane> specRating = new DoubleBetweenSpecification(
                new DoubleBetweenCriteria("rating", minRating, maxRating));

        @SuppressWarnings("unchecked")
        Specification specResult = Specification.where(specByName)
                .and(specByCountry)
                .and(specByAirplaneType)
                .and(specDateBetween)
                .and(specIsUsed)
                .and(specSpeed)
                .and(specCrewSize)
                .and(specRating);

        List<Airplane> airplanes = airplaneService.getAllAirplanes(specResult);
        return ResponseEntity.ok().body(airplanes);
    }

    @PostMapping(value = "/airplanes")
    public ResponseEntity<Airplane> createAirplane(@Validated(New.class) @RequestBody Airplane airplane) {

        if (airplane == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Вычисляем рейтинг согласно задания
        airplane.setRating(airplaneService.calcRating(airplane));

        airplaneService.createAirplane(airplane);
        return ResponseEntity.ok().body(airplane);
    }

    @PostMapping(value = "/airplanes/{id}")
    public ResponseEntity<Airplane> updateAirplane(@Validated(Update.class) @RequestBody Airplane airplane, @PathVariable Long id) {

        if (id == 0) {
            return ResponseEntity.badRequest().body(airplane);
        }


        Airplane checkAirplaneById = airplaneService.getAirplane(id);
        if (checkAirplaneById == null) {
            return ResponseEntity.notFound().build();
        }

        Field[] fields = airplane.getClass().getDeclaredFields();


        Airplane updateAirplane = new Airplane();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                    Object value = field.get(airplane);
                    if (value != null) {
                        field.set(updateAirplane, value);
                    } else {
                        value = field.get(checkAirplaneById);
                        field.set(updateAirplane, value);
                    }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


//        updateAirplane.setId(id);
//
//        if (airplane.getName() != null) {
//            updateAirplane.setName(airplane.getName());
//        } else {
//            updateAirplane.setName(checkAirplaneById.getName());
//        }
//
//        if (airplane.getCountry() != null) {
//            updateAirplane.setCountry(airplane.getCountry());
//        } else {
//            updateAirplane.setCountry(checkAirplaneById.getCountry());
//        }
//
//        if (airplane.getAirplaneType() != null) {
//            updateAirplane.setAirplaneType(airplane.getAirplaneType());
//        } else {
//            updateAirplane.setAirplaneType(checkAirplaneById.getAirplaneType());
//        }
//
//        updateAirplane.setUsed(airplane.isUsed());
//
//        if (airplane.getProdDate() != null) {
//            updateAirplane.setProdDate(airplane.getProdDate());
//        } else {
//            updateAirplane.setProdDate(checkAirplaneById.getProdDate());
//        }
//
//        if (airplane.getSpeed() != null) {
//            updateAirplane.setSpeed(airplane.getSpeed());
//        } else {
//            updateAirplane.setSpeed(checkAirplaneById.getSpeed());
//        }
//
//        if (airplane.getCrewSize() != null) {
//            updateAirplane.setCrewSize(airplane.getCrewSize());
//        } else {
//            updateAirplane.setCrewSize(checkAirplaneById.getCrewSize());
//        }

        // Вычисляем рейтинг согласно задания
        updateAirplane.setRating(airplaneService.calcRating(updateAirplane));

        airplaneService.updateAirplane(updateAirplane);
        return ResponseEntity.ok().body(updateAirplane);

    }

}
