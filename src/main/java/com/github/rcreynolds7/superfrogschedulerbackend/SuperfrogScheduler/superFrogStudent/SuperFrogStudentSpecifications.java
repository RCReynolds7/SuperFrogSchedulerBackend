package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class SuperFrogStudentSpecifications {

    public static Specification<SuperFrogStudent> withFirstName(String firstName) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(firstName)) {
                return builder.conjunction();
            }
            return builder.like(builder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
        };
    }

    public static Specification<SuperFrogStudent> withLastName(String lastName) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(lastName)) {
                return builder.conjunction();
            }
            return builder.like(builder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
        };
    }

    public static Specification<SuperFrogStudent> withEmail(String email) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(email)) {
                return builder.conjunction();
            }
            return builder.equal(root.get("email"), email);
        };
    }

    public static Specification<SuperFrogStudent> withPhone(String phone) {
        return (root, query, builder) -> {
            if (!StringUtils.hasText(phone)) {
                return builder.conjunction();
            }
            return builder.equal(root.get("phone"), phone);
        };
    }
}