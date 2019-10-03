package com.example.validation.hibenatevalidationdemo.controllers;

import com.example.validation.hibenatevalidationdemo.models.Airplane;
import com.example.validation.hibenatevalidationdemo.services.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Ivan Kurilov on 01.10.2019
 */
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class AirplaneController {


    private final AirplaneService airplaneService;

    @PostMapping(value = "/airplanes")
    public ResponseEntity<Airplane> createAirplane(@Valid @RequestBody Airplane airplane){

        if (airplane == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Вычисялем рейтинг согластно задания
        airplane.setRating(airplaneService.calcRating(airplane));

        airplaneService.createAirplane(airplane);
        return ResponseEntity.ok().body(airplane);
    }

}
