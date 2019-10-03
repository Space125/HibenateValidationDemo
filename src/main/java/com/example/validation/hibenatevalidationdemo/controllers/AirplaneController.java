package com.example.validation.hibenatevalidationdemo.controllers;

import com.example.validation.hibenatevalidationdemo.models.Airplane;
import com.example.validation.hibenatevalidationdemo.services.AirplaneService;
import com.example.validation.hibenatevalidationdemo.utils.New;
import com.example.validation.hibenatevalidationdemo.utils.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ivan Kurilov on 01.10.2019
 */
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class AirplaneController {


    private final AirplaneService airplaneService;

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

        Airplane updateAirplane = new Airplane();

        updateAirplane.setId(id);

        if (airplane.getName() != null) {
            updateAirplane.setName(airplane.getName());
        } else {
            updateAirplane.setName(checkAirplaneById.getName());
        }

        if (airplane.getCountry() != null) {
            updateAirplane.setCountry(airplane.getCountry());
        } else {
            updateAirplane.setCountry(checkAirplaneById.getCountry());
        }

        if (airplane.getAirplaneType() != null) {
            updateAirplane.setAirplaneType(airplane.getAirplaneType());
        } else {
            updateAirplane.setAirplaneType(checkAirplaneById.getAirplaneType());
        }

        updateAirplane.setUsed(airplane.isUsed());

        if (airplane.getProdDate() != null) {
            updateAirplane.setProdDate(airplane.getProdDate());
        } else {
            updateAirplane.setProdDate(checkAirplaneById.getProdDate());
        }

        if (airplane.getSpeed() != null) {
            updateAirplane.setSpeed(airplane.getSpeed());
        } else {
            updateAirplane.setSpeed(checkAirplaneById.getSpeed());
        }

        if (airplane.getCrewSize() != null) {
            updateAirplane.setCrewSize(airplane.getCrewSize());
        } else {
            updateAirplane.setCrewSize(checkAirplaneById.getCrewSize());
        }

        // Вычисляем рейтинг согласно задания
        updateAirplane.setRating(airplaneService.calcRating(updateAirplane));

        airplaneService.updateAirplane(updateAirplane);
        return ResponseEntity.ok().body(updateAirplane);

    }

}
