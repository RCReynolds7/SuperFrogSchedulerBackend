package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperFrogStudentRepository extends JpaRepository<SuperFrogStudent, Integer>, JpaSpecificationExecutor<SuperFrogStudent> {
}
