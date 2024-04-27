package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest;


import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.converter.AppearanceRequestDtoToAppearanceRequestConverter;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.converter.AppearanceRequestToAppearanceRequestDtoConverter;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.dto.AppearanceRequestDto;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.Result;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.StatusCode;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.enums.AppearanceRequestStatus;
import jakarta.validation.Valid;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/appearance-requests")
public class AppearanceRequestController {

    private final AppearanceRequestService appearanceRequestService;

    private final AppearanceRequestToAppearanceRequestDtoConverter appearanceRequestToAppearanceRequestDtoConverter;

    private final AppearanceRequestDtoToAppearanceRequestConverter appearanceRequestDtoToAppearanceRequestConverter;


    public AppearanceRequestController(AppearanceRequestService appearanceRequestService, AppearanceRequestToAppearanceRequestDtoConverter appearanceRequestToAppearanceRequestDtoConverter, AppearanceRequestDtoToAppearanceRequestConverter appearanceRequestDtoToAppearanceRequestConverter) {
        this.appearanceRequestService = appearanceRequestService;
        this.appearanceRequestToAppearanceRequestDtoConverter = appearanceRequestToAppearanceRequestDtoConverter;
        this.appearanceRequestDtoToAppearanceRequestConverter = appearanceRequestDtoToAppearanceRequestConverter;
    }

    @GetMapping("/{requestId}")
    public Result findAppearanceById(@PathVariable int requestId) {
        AppearanceRequest foundAppearanceRequest = this.appearanceRequestService.findById(requestId);
        AppearanceRequestDto appearanceRequestDto = this.appearanceRequestToAppearanceRequestDtoConverter.convert(foundAppearanceRequest);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", appearanceRequestDto);
    }

    @GetMapping("/")
    public Result getAppearanceRequests(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String eventTitle,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName

    ) {
        // Check if all search parameters are empty
        if (!StringUtils.hasText(firstName) && !StringUtils.hasText(lastName)
                && !StringUtils.hasText(id) && !StringUtils.hasText(eventTitle)) {
            return new Result(false, StatusCode.INVALID_ARGUMENT, "At least one search parameter must be provided");
        }


        List<AppearanceRequest> requests = appearanceRequestService.searchRequests(id, firstName, lastName, eventTitle);
        List<AppearanceRequestDto> resultDtos = requests.stream()
                .map(request -> new AppearanceRequestDto(
                        request.getId(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEventTitle(),
                        request.getEmail(),
                        request.getTypeOfEvent(),
                        request.getPhone(),
                        request.getEventAddress(),
                        request.getIsOnCampus(),
                        request.getSpecialInstructions(),
                        request.getExpensesOrBenefits(),
                        request.getOtherOrganizationsInvolved(),
                        request.getDetailedEventDescription(),
                        request.getSpecialInstructions(), request.getAppearanceRequestStatus(),
                        request.getDate()
                ))
                .collect(Collectors.toList());


        return new Result(true, StatusCode.SUCCESS, "Appearance request retrieved successfully", resultDtos);
    }

    @GetMapping("/status/{status}")
    public Result findAppearanceByStatus(@PathVariable AppearanceRequestStatus status) {
        List<AppearanceRequest> foundAppearance = this.appearanceRequestService.findByStatus(status);
        List<AppearanceRequestDto> appearanceRequestDtos = foundAppearance.stream()
                .map(this.appearanceRequestToAppearanceRequestDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Status Success", appearanceRequestDtos);
    }

    @PostMapping("/")
    public Result addAppearanceRequest(@Valid @RequestBody AppearanceRequestDto appearanceRequestDto) {
        AppearanceRequest newAppearance = this.appearanceRequestDtoToAppearanceRequestConverter.convert(appearanceRequestDto);
        AppearanceRequest savedAppearance = this.appearanceRequestService.save(newAppearance);
        AppearanceRequestDto savedAppearanceDto = this.appearanceRequestToAppearanceRequestDtoConverter.convert(savedAppearance);
        return new Result(true, StatusCode.SUCCESS, "Add Successful", savedAppearanceDto);
    }

    @PutMapping("/{requestId}")
    public Result updateAppearanceRequest(@PathVariable Integer requestId, @RequestBody AppearanceRequest appearanceRequestUpdate) {
        AppearanceRequest appearanceRequest = appearanceRequestService.findById(requestId);

        Optional.ofNullable(appearanceRequestUpdate.getFirstName())
                .filter(name -> !name.isEmpty())
                .ifPresent(appearanceRequest::setFirstName);

        Optional.ofNullable(appearanceRequestUpdate.getLastName())
                .filter(name -> !name.isEmpty())
                .ifPresent(appearanceRequest::setLastName);

        Optional.ofNullable(appearanceRequestUpdate.getEmail())
                .filter(email -> !email.isEmpty())
                .ifPresent(appearanceRequest::setEmail);

        Optional.ofNullable(appearanceRequestUpdate.getPhone())
                .filter(phone -> !phone.isEmpty())
                .ifPresent(appearanceRequest::setPhone);

        Optional.ofNullable(appearanceRequestUpdate.getEventAddress())
                .filter(eventAddress -> !eventAddress.isEmpty())
                .ifPresent(appearanceRequest::setEventAddress);

        Optional.ofNullable(appearanceRequestUpdate.getDate())
                .ifPresent(appearanceRequest::setDate);

        Optional.ofNullable(appearanceRequestUpdate.getTypeOfEvent())
                .filter(typeOfEvent -> !typeOfEvent.isEmpty())
                .ifPresent(appearanceRequest::setTypeOfEvent);

        Optional.ofNullable(appearanceRequestUpdate.getEventTitle())
                .filter(eventTitle -> !eventTitle.isEmpty())
                .ifPresent(appearanceRequest::setEventTitle);

        Optional.ofNullable(appearanceRequestUpdate.getNameOfOrg())
                .filter(nameOfOrg -> !nameOfOrg.isEmpty())
                .ifPresent(appearanceRequest::setNameOfOrg);

        Optional.ofNullable(appearanceRequestUpdate.getIsOnCampus())
                .filter(isOnCampus -> !isOnCampus.isEmpty())
                .ifPresent(appearanceRequest::setIsOnCampus);

        Optional.ofNullable(appearanceRequestUpdate.getSpecialInstructions())
                .filter(specialInstructions -> !specialInstructions.isEmpty())
                .ifPresent(appearanceRequest::setSpecialInstructions);

        Optional.ofNullable(appearanceRequestUpdate.getExpensesOrBenefits())
                .filter(expensesOrBenefits -> !expensesOrBenefits.isEmpty())
                .ifPresent(appearanceRequest::setExpensesOrBenefits);

        Optional.ofNullable(appearanceRequestUpdate.getOtherOrganizationsInvolved())
                .filter(otherOrganizationsInvolved -> !otherOrganizationsInvolved.isEmpty())
                .ifPresent(appearanceRequest::setOtherOrganizationsInvolved);

        Optional.ofNullable(appearanceRequestUpdate.getDetailedEventDescription())
                .filter(detailedEventDescription -> !detailedEventDescription.isEmpty())
                .ifPresent(appearanceRequest::setDetailedEventDescription);

        AppearanceRequest updatedAppearanceRequest = appearanceRequestService.update(requestId, appearanceRequest);

        return new Result(true, StatusCode.SUCCESS, "Appearance request information updated successfully", updatedAppearanceRequest);
    }

    @PutMapping("/{requestId}/status")
    public Result updateAppearanceRequestStatus(@PathVariable Integer requestId, @RequestBody AppearanceRequestDto appearanceRequestDto) {
        AppearanceRequest appearanceRequest = appearanceRequestService.findById(requestId);
        appearanceRequest.setAppearanceRequestStatus(appearanceRequestDto.appearanceRequestStatus());
        AppearanceRequest updatedRequest = this.appearanceRequestService.update(requestId, appearanceRequest);
        return new Result(true, StatusCode.SUCCESS, "Updated Status Success", updatedRequest);
    }

    @PutMapping("/{requestId}/cancel")
    public Result cancelAppearanceRequest(@PathVariable Integer requestId) {
        AppearanceRequest appearanceRequest = appearanceRequestService.findById(requestId);
        appearanceRequest.setAppearanceRequestStatus(AppearanceRequestStatus.CANCELED_BY_THE_SPIRIT_DIRECTOR);
        AppearanceRequest updatedRequest = this.appearanceRequestService.update(requestId, appearanceRequest);
        return new Result(true, StatusCode.SUCCESS, "Appearance Request Cancelled", updatedRequest);
    }



}
