package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.SuperFrogStudent;

import edu.tcu.cs.superfrogscheduler.superfrogrequest.AppearanceRequest;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SuperFrogAppearanceRequestService {
    private final AppearanceRequestRepository superFrogAppearanceRequestRepository;

    public AppearanceRequestService(SuperFrogAppearanceRequestRepository superFrogAppearanceRequestRepository) {
        this.superFrogAppearanceRequestRepository = superFrogAppearanceRequestRepository;
    }

    public AppearanceRequest findById(Integer requestId) {
        return this.superFrogAppearanceRequestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("superfrogappearancerequest", requestId));
    }


    public List<SuperFrogAppearanceRequest> findAll() {
        return this.superFrogAppearanceRequestRepository.findAll();
    }

    public List<SuperFrogAppearanceRequest> findByStatus(RequestStatus status) {
        return this.superFrogAppearanceRequestRepository.findByStatus(status);
    }

    public List<SuperFrogAppearanceRequest> findByStatusAndStudent(RequestStatus status, SuperFrogStudent student) {
        return this.superFrogAppearanceRequestRepository.findByStatusAndStudent(status, student);
    }
    public SuperFrogAppearanceRequest save(SuperFrogAppearanceRequest newSuperFrogAppearanceRequest) {
        return this.superFrogAppearanceRequestRepository.save(newSuperFrogAppearanceRequest);
    }

    public SuperFrogAppearanceRequest update(Integer requestId, SuperFrogAppearanceRequest update) {
        return this.superFrogAppearanceRequestRepository.findById(requestId)
                .map(oldRequest -> {
                    oldRequest.setEventType(update.getEventType());
                    oldRequest.setAddress(update.getAddress());
                    oldRequest.setEventDate(update.getEventDate());
                    oldRequest.setStartTime(update.getStartTime());
                    oldRequest.setEndTime(update.getEndTime());
                    oldRequest.setStatus(update.getStatus());
                    oldRequest.setContactFirstName(update.getFirstName());
                    oldRequest.setContactLastName(update.getLastName());
                    oldRequest.setPhoneNumber(update.getPhoneNumber());
                    oldRequest.setEmail(update.getEmail());
                    oldRequest.setTitle(update.getTitle());
                    oldRequest.setNameOfOrg(update.getNameOfOrg());
                    oldRequest.setDescription(update.getDescription());
                    oldRequest.setSpecialInstructions(update.getSpecialInstructions());
                    oldRequest.setexternalOrgs(update.getexternalOrgs());
                    oldRequest.setExpenses(update.getExpenses());
                    
                })
                .orElseThrow(() -> new ObjectNotFoundException("superfrogappearancerequest", requestId));
    }

    public void delete(Integer requestId) {
        this.AppearanceRequestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("superfrogappearancerequest", requestId));
        this.AppearanceRequestRepository.deleteById(requestId);
    }

    public AppearanceRequest updateStatus(Integer requestId, RequestStatus status) {
        return this.superFrogAppearanceRequestRepository.findById(requestId)
                .map(oldRequest -> {
                    oldRequest.setStatus(status);
                    return this.AppearanceRequestRepository.save(oldRequest);
                })
                .orElseThrow(() -> new ObjectNotFoundException("superfrogappearancerequest", requestId));
    }
}