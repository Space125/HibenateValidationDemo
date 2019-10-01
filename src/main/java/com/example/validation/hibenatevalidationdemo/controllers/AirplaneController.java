package com.example.validation.hibenatevalidationdemo.controllers;

import com.example.validation.hibenatevalidationdemo.AirplaneService;
import com.example.validation.hibenatevalidationdemo.AirplaneServiceImpl;
import com.example.validation.hibenatevalidationdemo.models.Airplane;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    @PostMapping(value = "/airplanes")
    public ResponseEntity<Airplane> createAirplane(@Valid @RequestBody Airplane airplane){

        if (airplane == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        airplane.setRating(airplaneService.calcRating(airplane));

        airplaneService.createAirplane(airplane);
        return ResponseEntity.ok().body(airplane);
    }

}
