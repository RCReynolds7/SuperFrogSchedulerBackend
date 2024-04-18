package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequest;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequestRepository;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequestService;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.Result;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public Result updateAppearanceRequest(@PathVariable Integer appearanceRequestId, @RequestBody AppearanceRequest appearanceRequestUpdate) {
        AppearanceRequest appearanceRequest = appearanceRequestService.findById(appearanceRequestId);

        Optional.ofNullable(appearanceRequestUpdate.getFirstName())
                .filter( name -> !name.isEmpty())
                .ifPresent(appearanceRequest::setFirstName);

        Optional.ofNullable(appearanceRequestUpdate.getLastName())
                .filter( name -> !name.isEmpty())
                .ifPresent(appearanceRequest::setLastName);

        Optional.ofNullable(appearanceRequestUpdate.getEmail())
                .filter( email -> !email.isEmpty())
                .ifPresent(appearanceRequest::setEmail);

    }

}



