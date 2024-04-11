package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
}