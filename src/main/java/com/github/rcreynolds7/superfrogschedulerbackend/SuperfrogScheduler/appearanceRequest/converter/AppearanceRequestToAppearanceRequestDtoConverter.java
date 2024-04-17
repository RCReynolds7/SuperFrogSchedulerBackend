//package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.converter;
//
//import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequest;
//import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.dto.AppearanceRequestDto;
//import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.converter.SuperFrogToSuperFrogDtoConverter;
//import jakarta.persistence.Convert;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class AppearanceRequestToAppearanceRequestDtoConverter implements Converter<AppearanceRequest, AppearanceRequestDto> {
//    private final SuperFrogToSuperFrogDtoConverter superFrogToSuperFrogDtoConverter;
//
//    public AppearanceRequestToAppearanceRequestDtoConverter(SuperFrogToSuperFrogDtoConverter superFrogToSuperFrogDtoConverter) {
//        this.superFrogToSuperFrogDtoConverter = superFrogToSuperFrogDtoConverter;
//    }
//
//    @Override
//    public AppearanceRequestDto convert(AppearanceRequest source) {
//        return new AppearanceRequestDto(
//                source.getId(),
//                source.getTypeOfEvent(),
//                source.getEventAddress(),
//                source.getIsOnCampus(),
//                source.getSpecialInstructions(),
//                source.getExpensesOrBenefits(),
//                source.getFirstName(),
//                source.getOtherOrganizationsInvolved(),
//                source.getLastName(),
//                source.getPhone(),
//                source.getAppearanceRequestStatus(),
//                source.getEmail(),
//                source.getEventTitle(),
//                source.getDetailedEventDescription(),
//                source.getSpecialInstructions(),
//                source.getDate(),
//                source.getAssignedSuperFrogStudent() != null ? this.superFrogToSuperFrogDtoConverter.convert(source.getAssignedSuperFrogStudent()) : null);
//    }
//}
