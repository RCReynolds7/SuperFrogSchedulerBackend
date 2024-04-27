package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.dto;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.enums.AppearanceRequestStatus;
import jakarta.validation.constraints.NotEmpty;

public record AppearanceRequestStatusDto(@NotEmpty(message = "appearenceRequestStatus required.") AppearanceRequestStatus appearanceRequestStatus) {
}
