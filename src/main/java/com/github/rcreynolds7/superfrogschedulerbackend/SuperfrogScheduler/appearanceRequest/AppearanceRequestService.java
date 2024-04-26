package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.enums.AppearanceRequestStatus;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AppearanceRequestService {
    private final AppearanceRequestRepository appearanceRequestRepository;

    public AppearanceRequest createAppearanceRequest(AppearanceRequest request) {
        return this.appearanceRequestRepository.save(request);
    }

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

    public AppearanceRequest update(Integer requestId, AppearanceRequest update) {
        AppearanceRequest appearanceRequest = this.appearanceRequestRepository
                .findById(requestId)
                .map(oldAppearanceRequest -> {
                    oldAppearanceRequest.setFirstName(update.getFirstName());
                    oldAppearanceRequest.setLastName(update.getLastName());
                    oldAppearanceRequest.setPhone(update.getPhone());
                    oldAppearanceRequest.setEmail(update.getEmail());
                    oldAppearanceRequest.setDate(update.getDate());
                    oldAppearanceRequest.setTypeOfEvent(update.getTypeOfEvent());
                    oldAppearanceRequest.setEventTitle(update.getEventTitle());
                    oldAppearanceRequest.setNameOfOrg(update.getNameOfOrg());
                    oldAppearanceRequest.setEventAddress(update.getEventAddress());
                    oldAppearanceRequest.setIsOnCampus(update.getIsOnCampus());
                    oldAppearanceRequest.setSpecialInstructions(update.getSpecialInstructions());
                    oldAppearanceRequest.setExpensesOrBenefits(update.getExpensesOrBenefits());
                    oldAppearanceRequest.setOtherOrganizationsInvolved(update.getOtherOrganizationsInvolved());
                    oldAppearanceRequest.setDetailedEventDescription(update.getDetailedEventDescription());
                    return oldAppearanceRequest;
                })
                .orElseThrow(() -> new ObjectNotFoundException("appearanceRequest", requestId));
        return appearanceRequestRepository.save(appearanceRequest);
    }

    public void deleteAppearanceRequest(Integer requestId) {
        this.appearanceRequestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("appearanceRequest", requestId));
        this.appearanceRequestRepository.deleteById(requestId);
    }

    public List<AppearanceRequest> searchRequests(String id, String firstName, String lastName, String eventTitle) {
        Specification<AppearanceRequest> spec = Specification.where(null);
        if (StringUtils.hasText(firstName)) {
            spec = spec.and(AppearanceRequestSpecifications.withFirstName(firstName));
        }
        if (StringUtils.hasText(lastName)) {
            spec = spec.and(AppearanceRequestSpecifications.withLastName(lastName));
        }
        if (StringUtils.hasText(eventTitle)) {
            spec = spec.and(AppearanceRequestSpecifications.withEventTitle(eventTitle));
        }
        if (StringUtils.hasText(id)) {
            spec = spec.and(AppearanceRequestSpecifications.withId(id));
        }
        return appearanceRequestRepository.findAll(spec);
    }

    public AppearanceRequest updateStatus(Integer requestId, AppearanceRequestStatus status) {
        return this.appearanceRequestRepository.findById(requestId)
                .map(oldRequest -> {
                    oldRequest.setAppearanceRequestStatus(status);
                    return this.appearanceRequestRepository.save(oldRequest);
                })
                .orElseThrow(() -> new ObjectNotFoundException("appearancerequest", requestId));

    }

    public List<AppearanceRequest> findByStatus(AppearanceRequestStatus status) {
        return this.appearanceRequestRepository.findByStatus(status);
    }

    public List<AppearanceRequest> findByStatusAndStudent(AppearanceRequestStatus status, SuperFrogStudent student) {
        return this.appearanceRequestRepository.findByStatusAndStudent(status, student);
    }
}
