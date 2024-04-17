package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.converter;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequest;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.dto.AppearanceRequestDto;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.enums.AppearanceRequestStatus;
import jakarta.persistence.Convert;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AppearanceRequestDtoToAppearanceRequestConverter implements Converter<AppearanceRequestDto, AppearanceRequest> {

    @Override
    public AppearanceRequest convert(AppearanceRequestDto source) {
        AppearanceRequest appearanceRequest = new AppearanceRequest();
        appearanceRequest.setId(source.requestId());
        appearanceRequest.setTypeOfEvent(source.typeOfEvent());
        appearanceRequest.setEventAddress(source.eventAddress());
        appearanceRequest.setDate(source.date());
        appearanceRequest.setAppearanceRequestStatus(AppearanceRequestStatus.valueOf(source.appearanceRequestStatus()));
        appearanceRequest.setFirstName(source.firstName());
        appearanceRequest.setLastName(source.lastName());
        appearanceRequest.setPhone(source.phone());
        appearanceRequest.setEmail(source.email());
        appearanceRequest.setEventTitle(source.eventTitle());
        appearanceRequest.setDetailedEventDescription(source.detailedEventDescription());
        appearanceRequest.setOtherOrganizationsInvolved(source.otherOrganizationsInvolved());
        appearanceRequest.setSpecialInstructions(source.specialInstructions());
        appearanceRequest.setExpensesOrBenefits(source.expensesOrBenefits());
        return appearanceRequest;
    }
}