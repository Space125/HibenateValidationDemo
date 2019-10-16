package com.example.validation.hibenatevalidationdemo.specifications;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * @author Ivan Kurilov on 11.10.2019
 */
@Data
@RequiredArgsConstructor
public class DateBetweenCriteria {
    private final Date after;
    private final Date before;
}
