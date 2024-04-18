package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequest;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequestDetails;
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

   @GetMapping("appearance-request/{requestId}/details")
  public Result getAppearanceRequestDetails(@PathVariable Integer requestId) {
       AppearanceRequestDetails request = appearanceRequestService.getDetails(requestId);
       return new Result(true, StatusCode.SUCCESS, "Appearance request details retrieved successfully", request);
   }

    @PostMapping("/request-superfrog-appearance")
    public Result requestSuperFrogAppearance(@RequestBody AppearanceRequest appearanceRequest) {
        // Create appearance request service in the database
        AppearanceRequest savedAppearanceRequest = this.appearanceRequestService.save(appearanceRequest);

        return new Result(true, StatusCode.SUCCESS, "SuperFrog appearance request created successfully.", savedAppearanceRequest);
    }

    @PutMapping("/appearance-requests/{requestId}")
    public Result updateAppearanceRequest(@PathVariable Integer appearanceRequestId, @RequestBody AppearanceRequest appearanceRequestUpdate) {
        AppearanceRequest appearanceRequest = appearanceRequestService.findById(appearanceRequestId);

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

        appearanceRequestService.updateAppearanceRequest(appearanceRequestId, appearanceRequest);

        return new Result(true, StatusCode.SUCCESS, "Appearance request information updated successfully");
    }

    @DeleteMapping("/appearance-requests/{requestId}")
    public Result deleteAppearanceRequest(@PathVariable Integer requestId) {
        this.appearanceRequestService.deleteAppearanceRequest(requestId);
        return new Result(true, StatusCode.SUCCESS, "Appearance Request deleted successfully.");
    }
}



