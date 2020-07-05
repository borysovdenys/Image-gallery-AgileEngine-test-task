package com.borysov.agileengine.specifications;

import com.borysov.agileengine.models.Item;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CustomSpecs {

    public static Specification<Item> containsText(final String text) {
        return new Specification<Item>() {
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {

                String finalText = text;

                if (!text.contains("%")) {
                    finalText = "%" + text + "%";
                }
                return builder.or(
                        builder.like(root.get("author"), finalText),
                        builder.like(root.get("camera"), finalText),
                        builder.like(root.get("tags"), finalText)
                );
            }
        };
    }

}
