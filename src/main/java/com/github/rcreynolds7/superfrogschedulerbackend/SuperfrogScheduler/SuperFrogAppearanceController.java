package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.SuperFrogStudent;

import jakarta.validation.Valid;
import org.apache.coyote.Request;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SuperFrogAppearanceController {

    private final AppearanceRequestService AppearanceRequestService;

    private final SuperFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter;

    private final SuperFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter superFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter;

    public SuperFrogAppearanceController(AppearanceRequestService AppearanceRequestService, SuperFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter, SuperFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter superFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter) {
        this.AppearanceRequestService = AppearanceRequestService;
        this.superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter = superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter;
        this.superFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter = superFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter;
    }

   
    public Result findSuperFrogAppearanceById(@PathVariable int requestId) {
        SuperFrogAppearanceRequest foundSuperFrogAppearance = this.superFrogAppearanceRequestService.findById(requestId);
        SuperFrogAppearanceRequestDto superFrogAppearanceRequestDto = this.superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter.convert(foundSuperFrogAppearance);
        return new Result(true, HttpStatusCode.SUCCESS, "Find One Success", superFrogAppearanceRequestDto);
    }

    public Result findAllSuperFrogAppearances() {
        List<AppearanceRequest> foundAppearance = this.AppearanceRequestService.findAll();

        List<AppearanceRequestDto> appearanceRequestDtos = foundAppearance.stream()
                .map(this.superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, HttpStatusCode.SUCCESS, "Find All Success", appearanceRequestDtos);
    }

   
    public Result findSuperFrogAppearanceByStatus(@PathVariable RequestStatus status) {
        List<AppearanceRequest> foundAppearance = this.AppearanceRequestService.findByStatus(status);

        List<SuperFrogAppearanceRequestDto> appearanceRequestDtos = foundAppearance.stream()
                .map(this.superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, HttpStatusCode.SUCCESS, "Find By Status Success", appearanceRequestDtos);
    }

    public Result addSuperFrogAppearanceRequest(@Valid @RequestBody SuperFrogAppearanceRequestDto appearanceRequestDto) {
        AppearanceRequest newAppearance = this.AppearanceRequestDtoToSuperFrogAppearanceRequestConverter.convert(appearanceRequestDto);
        AppearanceRequest savedAppearance = this.AppearanceRequestService.save(newAppearance);
        SuperFrogAppearanceRequestDto savedAppearanceDto = this.superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter.convert(savedAppearance);
        return new Result(true, HttpStatusCode.SUCCESS, "Add Success", savedAppearanceDto);
    }


    public Result updateSuperFrogAppearanceRequest(@PathVariable Integer requestId, @Valid @RequestBody SuperFrogAppearanceRequestDto superFrogAppearanceRequestDto) {
        AppearanceRequest update = this.superFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter.convert(superFrogAppearanceRequestDto);
        AppearanceRequest updatedRequest = this.AppearanceRequestService.update(requestId, update);
        SuperFrogAppearanceRequestDto updatedRequestDto = this.superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter.convert(updatedRequest);
        return new Result(true, HttpStatusCode.SUCCESS, "Update Success", updatedRequestDto);
    }

    
    public Result updateSuperFrogAppearanceRequest(@PathVariable Integer requestId,@PathVariable RequestStatus status) {
        AppearanceRequest updatedRequest = this.AppearanceRequestService.updateStatus(requestId, status);
        SuperFrogAppearanceRequestDto updatedRequestDto = this.superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter.convert(updatedRequest);
        return new Result(true, HttpStatusCode.SUCCESS, "Update Success", updatedRequestDto);
    }

    public Result deleteSuperFrogAppearanceRequest(@PathVariable Integer requestId){
        this.superFrogAppearanceRequestService.delete(requestId);
        return new Result(true, HttpStatusCode.SUCCESS, "Delete Success");
    }


}