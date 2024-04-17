package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.converter;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequest;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.dto.AppearanceRequestDto;
import jakarta.persistence.Convert;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AppearanceRequestDtoToAppearanceRequestConverter implements Converter<AppearanceRequestDto, AppearanceRequest> {

    @Override
    public AppearanceRequest convert(AppearanceRequestDto source) {
        AppearanceRequest  = new AppearanceRequest();
        AppearanceRequest.setRequestId(source.requestId());
        AppearanceRequest.setEventType(source.eventType());
        AppearanceRequest.setAddress(source.address());
        AppearanceRequest.setEventDate(source.eventDate());
        // AppearanceRequest.setStartTime(source.startTime());
       // AppearanceRequest.setEndTime(source.endTime());
        AppearanceRequest.setStatus(source.status());
        AppearanceRequest.setFirstName(source.firstName());
        AppearanceRequest.setLastName(source.lastName());
        AppearanceRequest.setPhone(source.phone());
        AppearanceRequest.setEmail(source.email());
        AppearanceRequest.setEventTitle(source.eventTitle());
        AppearanceRequest.setNameOfOrg(source.nameOfOrg());
        AppearanceRequest.setEventDescription(source.eventDescription());
        AppearanceRequest.setOutsideOrgs(source.outsideOrgs());
        AppearanceRequest.setSpecialInstructions(source.specialInstructions());
        AppearanceRequest.setExpenses(source.expenses());
        return ;
    }

    @Override
    public AppearanceRequest convert(AppearanceRequestDto source) {
        return null;
    }
}