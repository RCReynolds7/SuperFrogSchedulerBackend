package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;


public class AppearanceRequestSpecifications {

    public static Specification<AppearanceRequest> withFirstName(String firstName) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(firstName)) {
                return builder.conjunction();
            }
            return builder.like(builder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
        };
    }

    public static Specification<AppearanceRequest> withLastName(String lastName) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(lastName)) {
                return builder.conjunction();
            }
            return builder.like(builder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
        };
    }

    public static Specification<AppearanceRequest> withEventTitle(String eventTitle) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(eventTitle)){
                return builder.conjunction();
            }
            return builder.equal(root.get("event title"), eventTitle);
        };
    }

    public static Specification<AppearanceRequest> withId(String id) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(id)){
                return builder.conjunction();
            }
            return builder.equal(root.get("id"), id);
        };
    }

}
