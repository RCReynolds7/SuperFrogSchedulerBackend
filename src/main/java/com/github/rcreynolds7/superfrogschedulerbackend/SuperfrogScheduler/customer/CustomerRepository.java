package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
