package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.converter;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequest;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.dto.AppearanceRequestDto;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.converter.SuperFrogStudentToSuperFrogStudentDtoConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class AppearanceRequestToAppearanceRequestDtoConverter implements Converter<AppearanceRequest, AppearanceRequestDto> {
    
    @Override
    public AppearanceRequestDto convert(AppearanceRequest source) {
        AppearanceRequestDto appearanceRequestDto = new AppearanceRequestDto(
                source.getId(),
                source.getTypeOfEvent(),
                source.getEventAddress(),
                source.getIsOnCampus(),
                source.getSpecialInstructions(),
                source.getExpensesOrBenefits(),
                source.getFirstName(),
                source.getOtherOrganizationsInvolved(),
                source.getLastName(),
                source.getPhone(),
                source.getEmail(),
                source.getEventTitle(),
                source.getDetailedEventDescription(),
                source.getSpecialInstructions(),
                source.getAppearanceRequestStatus(),
                source.getDate());

                //source.getStudent() != null ? this.superFrogToSuperFrogDtoConverter.convert(source.getStudent()) : null);
        return appearanceRequestDto;
    }
}
