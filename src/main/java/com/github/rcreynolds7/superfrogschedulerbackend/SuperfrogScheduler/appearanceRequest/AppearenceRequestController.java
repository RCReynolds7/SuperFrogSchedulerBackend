package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer.CustomerService;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.Result;
import org.apache.catalina.connector.Request;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppearenceRequestController {

    private final AppearanceRequestService appearanceRequestService;
    private final AppearanceRequestToAppearanceRequestDtoConverter AppearanceRequestToAppearanceRequestDtoConverter;

    private final AppearanceRequestDtoToAppearanceRequestConverter AppearanceRequestDtoToAppearanceRequestConverter;




    public AppearenceRequestController(AppearanceRequestService appearanceRequestService) {
        this.appearanceRequestService = appearanceRequestService;
    }




    }
}
