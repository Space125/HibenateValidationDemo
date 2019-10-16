package com.example.validation.hibenatevalidationdemo.specifications;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Ivan Kurilov on 11.10.2019
 */
@Data
@RequiredArgsConstructor
public class DoubleBetweenCriteria {
    private final String key;
    private final Double min;
    private final Double max;
}
