package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.dto;

import jakarta.validation.constraints.NotEmpty;

public record AppearanceRequestDto (int requestId,
                                    @NotEmpty(message = "firstname is required.")  String firstName,
                                    @NotEmpty(message = "lastname is required.") String lastName,
                                    @NotEmpty(message = "email is required.") String email,
                                    @NotEmpty(message = "phone is required.") String phone,
                                    @NotEmpty(message = "type of event is required.") String typeOfEvent,
                                    @NotEmpty(message = "title of event is required.") String eventTitle,
                                    @NotEmpty(message = "event address is required.") String eventAddress,
                                    @NotEmpty(message = "whether on TCU campus or not is required.") String isOnCampus,
                                    @NotEmpty(message = "special instructions are required.") String specialInstructions,
                                    @NotEmpty(message = "expenses or benefits are required.") String expensesOrBenefits,
                                    @NotEmpty(message = "other organizations involved are required.") String otherOrganizationsInvolved,
                                    @NotEmpty(message = "detailed event descripiton required.") String detailedEventDescription
) {
}
