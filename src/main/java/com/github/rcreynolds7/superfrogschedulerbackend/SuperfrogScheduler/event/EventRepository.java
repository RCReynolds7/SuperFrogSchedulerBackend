package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
}
