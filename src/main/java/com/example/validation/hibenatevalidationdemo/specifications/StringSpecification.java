package com.example.validation.hibenatevalidationdemo.specifications;

import com.example.validation.hibenatevalidationdemo.models.Airplane;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Kurilov on 09.10.2019
 */

@RequiredArgsConstructor
public class StringSpecification implements Specification<Airplane> {

    private final StringCriteria stringCriteria;

    @Override
    public Predicate toPredicate(Root<Airplane> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

//        if (stringCriteria.getOperation().equalsIgnoreCase(">") && stringCriteria.getValue() != null) {
//            predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(
//                    root.get(stringCriteria.getKey()), stringCriteria.getValue().toString())));
//
//        } else if (stringCriteria.getOperation().equalsIgnoreCase("<") && stringCriteria.getValue() != null) {
//            predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(
//                    root.get(stringCriteria.getKey()), stringCriteria.getValue().toString())));
//
//        } else

        if (stringCriteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(stringCriteria.getKey()).getJavaType() == String.class && stringCriteria.getValue() != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(
                        root.get(stringCriteria.getKey()), "%" + stringCriteria.getValue() + "%")));

            } else if (stringCriteria.getValue() != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(
                        root.<String>get(stringCriteria.getKey()), stringCriteria.getValue())));
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
