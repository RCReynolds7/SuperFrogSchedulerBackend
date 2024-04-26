package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest;


import org.springframework.web.bind.annotation.RestController;

@RestController

public class AppearanceRequestController {

    private final AppearanceRequestService appearanceRequestService;


    public AppearanceRequestController(AppearanceRequestService appearanceRequestService) {
        this.appearanceRequestService = appearanceRequestService;
    }




}
