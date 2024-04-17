package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SuperFrogStudentDto (
        Integer id,

        @NotEmpty(message = "First name is required.")
        String firstName,

        @NotEmpty(message = "Last name is required.")
        String lastName,

        @NotEmpty(message = "Email is required.")
        @Email(message = "Email should be valid.")
        String email,

        @NotBlank(message = "Phone number is required.")
        String phone,

        @NotBlank(message = "Address is required.")
        String address, // Optional

        @NotNull(message = "Active status is required.")
        Boolean isActive
) {
}