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
 * @author Ivan Kurilov on 11.10.2019
 */
@RequiredArgsConstructor
public class DateBetweenSpecification implements Specification<Airplane> {

    private final DateBetweenCriteria dateBetweenCriteria;

    @Override
    public Predicate toPredicate(Root<Airplane> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if(dateBetweenCriteria.getAfter() != null && dateBetweenCriteria.getBefore() != null){
            predicates.add(criteriaBuilder.between(root.get("prodDate"),
                            dateBetweenCriteria.getAfter(), dateBetweenCriteria.getBefore()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
