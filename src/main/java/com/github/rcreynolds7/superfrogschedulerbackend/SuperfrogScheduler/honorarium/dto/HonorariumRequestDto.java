package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.honorarium.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record HonorariumRequestDto(Integer superFrogStudentId, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate) {
}
