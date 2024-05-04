package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuperFrogStudentRepository extends JpaRepository<SuperFrogStudent, Integer>, JpaSpecificationExecutor<SuperFrogStudent> {
    Optional<SuperFrogStudent> findByEmail(String email);
}
