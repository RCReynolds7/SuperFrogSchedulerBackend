package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.honorarium.dto;

public record HonorariumResponseDto(
        Integer superFrogStudentId,
        com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.enums.PaymentPreference paymentPreference,
        Boolean international,
        String address,
        Integer amount
) {}