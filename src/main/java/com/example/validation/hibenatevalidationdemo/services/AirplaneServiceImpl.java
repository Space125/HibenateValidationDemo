package com.example.validation.hibenatevalidationdemo.services;

import com.example.validation.hibenatevalidationdemo.models.Airplane;
import com.example.validation.hibenatevalidationdemo.repositories.AirplaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Ivan Kurilov on 01.10.2019
 */
@Service
@RequiredArgsConstructor
public class AirplaneServiceImpl implements AirplaneService {


    private final AirplaneRepository airplaneRepository;

    @Override
    public List<Airplane> getAllAirplanes() {
        return null;
    }

    @Override
    public List<Airplane> getAllAirplanes(Integer pageNumber, Integer pageSize, String sortBy) {
        return null;
    }

    @Override
    public void createAirplane(Airplane airplane) {
        airplaneRepository.save(airplane);
    }

    @Override
    public void updateAirplane(Airplane airplane) {
        airplaneRepository.save(airplane);
    }

    @Override
    public void deleteAirplane(Long id) {

    }

    @Override
    public Airplane getAirplane(Long id) {

        return airplaneRepository.findById(id).orElse(null);
    }

    @Override
    public Integer getCount() {
        return null;
    }

    @Override
    public Double calcRating(Airplane airplane) {
        double k;
        if (airplane.isUsed()){
            k = 0.5;
        } else {
            k = 1.0;
        }

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
        calendar.setTime(airplane.getProdDate());

        int prodDate = calendar.get(Calendar.YEAR);

        return new BigDecimal((80 * airplane.getSpeed() * k) / (3019 - prodDate + 1))
                .setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }
}
