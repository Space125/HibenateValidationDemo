package com.example.validation.hibenatevalidationdemo.repositories;

import com.example.validation.hibenatevalidationdemo.models.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ivan Kurilov on 01.10.2019
 */
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
}
