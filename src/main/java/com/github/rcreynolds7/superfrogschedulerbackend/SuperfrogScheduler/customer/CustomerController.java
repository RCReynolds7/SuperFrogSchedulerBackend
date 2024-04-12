package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequest;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequestService;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.Result;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.StatusCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.endpoint.base-url}/customer")
public class CustomerController {
    private final AppearanceRequestService appearanceRequestService;
    private final CustomerService customerService;

    public CustomerController(AppearanceRequestService appearanceRequestService, CustomerService customerService) {
        this.appearanceRequestService = appearanceRequestService;
        this.customerService = customerService;
    }

    @PostMapping("/request-superfrog-appearance")
    public Result requestSuperfrogAppearance(@RequestBody AppearanceRequest appearanceRequest) {

        // Create appearance request service in the database
        appearanceRequestService.save(appearanceRequest);

        return new Result(true, StatusCode.SUCCESS, "SuperFrog appearance request created successfully.");
    }
}
