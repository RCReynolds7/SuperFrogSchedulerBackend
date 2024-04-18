package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.enums.AppearanceRequestStatus;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AppearanceRequestService {
    private final AppearanceRequestRepository appearanceRequestRepository;

    public AppearanceRequestService(AppearanceRequestRepository appearanceRequestRepository) {
        this.appearanceRequestRepository = appearanceRequestRepository;
    }

    public AppearanceRequest findById(Integer requestId) {
        return this.appearanceRequestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("AppearanceRequest", requestId));
    }


    public List<AppearanceRequest> findAll() {
        return this.appearanceRequestRepository.findAll();
    }

    public AppearanceRequest save(AppearanceRequest newAppearanceRequest) {
        return this.appearanceRequestRepository.save(newAppearanceRequest);
    }

    public List<AppearanceRequest> findCompletedBySuperFrogStudentIdAndDateRange(SuperFrogStudent superFrogStudent, LocalDateTime startDate, LocalDateTime endDate) {
        return appearanceRequestRepository.findByAssignedSuperFrogStudentAndAppearanceRequestStatusInAndDateBetween(
                superFrogStudent,
                List.of(AppearanceRequestStatus.COMPLETED),
                startDate,
                endDate
        );
    }

    public void updateStatusToSubmittedToPayroll(List<AppearanceRequest> requests) {
        requests.forEach(request -> {
            request.setAppearanceRequestStatus(AppearanceRequestStatus.SUBMITTED_TO_PAYROLL);
        });
    }

    public AppearanceRequest updateAppearanceRequest(Integer id, AppearanceRequest requestDetails) {
        AppearanceRequest appearanceRequest = this.appearanceRequestRepository
                .findById(id)
                .map(oldAppearanceRequest -> {
                    oldAppearanceRequest.setFirstName(requestDetails.getFirstName());
                    oldAppearanceRequest.setLastName(requestDetails.getLastName());
                    oldAppearanceRequest.setPhone(requestDetails.getPhone());
                    oldAppearanceRequest.setEmail(requestDetails.getEmail());
                    oldAppearanceRequest.setDate(requestDetails.getDate());
                    oldAppearanceRequest.setTypeOfEvent(requestDetails.getTypeOfEvent());
                    oldAppearanceRequest.setEventTitle(requestDetails.getEventTitle());
                    oldAppearanceRequest.setNameOfOrg(requestDetails.getNameOfOrg());
                    oldAppearanceRequest.setEventAddress(requestDetails.getEventAddress());
                    oldAppearanceRequest.setIsOnCampus(requestDetails.getIsOnCampus());
                    oldAppearanceRequest.setSpecialInstructions(requestDetails.getSpecialInstructions());
                    oldAppearanceRequest.setExpensesOrBenefits(requestDetails.getExpensesOrBenefits());
                    oldAppearanceRequest.setOtherOrganizationsInvolved(requestDetails.getOtherOrganizationsInvolved());
                    oldAppearanceRequest.setDetailedEventDescription(requestDetails.getDetailedEventDescription());
                    return oldAppearanceRequest;
                })
                .orElseThrow(() -> new ObjectNotFoundException("appearanceRequest", id));
        return appearanceRequest.save(appearanceRequest);
    }
    public void deleteAppearanceRequest(Integer id) {
        this.appearanceRequestRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("appearanceRequest", id));
    }

}