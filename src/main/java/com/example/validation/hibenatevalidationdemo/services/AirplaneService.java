package com.example.validation.hibenatevalidationdemo.services;

import com.example.validation.hibenatevalidationdemo.models.Airplane;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @author Ivan Kurilov on 01.10.2019
 */
public interface AirplaneService {
    List<Airplane> getAllAirplanes(Specification spec);

    List<Airplane> getAllAirplanes(Integer pageNumber, Integer pageSize, String sortBy);

    void createAirplane(Airplane airplane);

    void updateAirplane(Airplane airplane);

    void deleteAirplane(Long id);

    Airplane getAirplane(Long id);

    Integer getCount();

    Double calcRating(Airplane airplane);

}
