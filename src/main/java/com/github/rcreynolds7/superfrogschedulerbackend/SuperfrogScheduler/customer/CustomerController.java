package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequest;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequestRepository;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequestService;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.Result;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.StatusCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.endpoint.base-url}/customer")
public class CustomerController {
    private final AppearanceRequestService appearanceRequestService;
    private final CustomerService customerService;

    private final AppearanceRequestRepository appearanceRequestRepository;

    public CustomerController(AppearanceRequestService appearanceRequestService, CustomerService customerService, AppearanceRequestRepository appearanceRequestRepository) {
        this.appearanceRequestService = appearanceRequestService;
        this.customerService = customerService;
        this.appearanceRequestRepository = appearanceRequestRepository;
    }

    @PostMapping("/request-superfrog-appearance")
    public Result requestSuperFrogAppearance(@RequestBody AppearanceRequest appearanceRequest) {
        // Create appearance request service in the database
        AppearanceRequest savedAppearanceRequest = this.appearanceRequestService.save(appearanceRequest);


        return new Result(true, StatusCode.SUCCESS, "SuperFrog appearance request created successfully.", savedAppearanceRequest);
    }

    @PutMapping("/appearance-requests/{appearanceRequestId}")

    //updateevent on eventservice

    public Result updateSuperFrogAppearance(@RequestBody AppearanceRequest appearanceRequest) {
        Result result = this.appearanceRequestRepository
                .findById(RequestId)
                .map(oldAppearenceRequestId -> {
                oldAppearenceRequestId.setDate(appearanceRequest.getDate());



                })



}



