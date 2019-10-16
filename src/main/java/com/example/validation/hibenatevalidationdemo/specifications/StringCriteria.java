package com.example.validation.hibenatevalidationdemo.specifications;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Ivan Kurilov on 09.10.2019
 */
@Data
@RequiredArgsConstructor
public class StringCriteria {
    private final String key;
    private final String operation;
    private final Object value;
}
