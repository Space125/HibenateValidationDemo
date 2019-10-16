package com.example.validation.hibenatevalidationdemo.specifications;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Ivan Kurilov on 11.10.2019
 */
@Data
@RequiredArgsConstructor
public class BooleanCriteria {
    private final Boolean isUsed;
}
