package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.SuperFrogStudent;

import java.time.LocalDate;
import java.time.LocalTime;

public record SuperFrogAppearanceRequestDto(
        Integer requestId,

        EventType eventType,

        String address,

        LocalDate eventDate,

        LocalTime startTime,

        LocalTime endTime,
        RequestStatus status,

        String FirstName,

        String LastName,

        String phoneNumber,

        String email,

        String title,

        String nameOfOrg,

        String description,

        String specialInstructions,

        String externalOrgs,

        String expenses,
        
        SuperFrogStudentDto student) {
}