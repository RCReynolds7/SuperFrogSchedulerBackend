package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequest;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequestRepository;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.enums.AppearanceRequestStatus;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class SuperFrogStudentService {
    private final SuperFrogStudentRepository superFrogStudentRepository;
    private final AppearanceRequestRepository appearanceRequestRepository;

    @Autowired
    public SuperFrogStudentService(SuperFrogStudentRepository superFrogStudentRepository, AppearanceRequestRepository appearanceRequestRepository) {
        this.superFrogStudentRepository = superFrogStudentRepository;
        this.appearanceRequestRepository = appearanceRequestRepository;
    }

    public SuperFrogStudent findById(Integer superFrogStudentId) {
        return this.superFrogStudentRepository.findById(superFrogStudentId).
                orElseThrow(() -> new ObjectNotFoundException("superfrogstudent", superFrogStudentId));
    }

    public List<SuperFrogStudent> findAll() {
        return this.superFrogStudentRepository.findAll();
    }

    public SuperFrogStudent update(Integer superFrogStudentId, SuperFrogStudent update) {
        return this.superFrogStudentRepository.findById(superFrogStudentId)
                .map(oldSuperFrogStudent -> {
                    oldSuperFrogStudent.setFirstName(update.getFirstName());
                    oldSuperFrogStudent.setLastName(update.getLastName());
                    oldSuperFrogStudent.setEmail(update.getEmail());
                    oldSuperFrogStudent.setPhone(update.getPhone());
                    oldSuperFrogStudent.setAddress(update.getAddress());
                    oldSuperFrogStudent.setActive(update.getActive());
                    oldSuperFrogStudent.setInternational(update.getInternational());
                    oldSuperFrogStudent.setPaymentPreference(update.getPaymentPreference());

                    return this.superFrogStudentRepository.save(oldSuperFrogStudent);
                })
                .orElseThrow(() -> new ObjectNotFoundException("superfrogstudent", superFrogStudentId));
    }

    public List<SuperFrogStudent> searchStudents(String firstName, String lastName, String email, String phone) {
        Specification<SuperFrogStudent> spec = Specification.where(null);
        if (StringUtils.hasText(firstName)) {
            spec = spec.and(SuperFrogStudentSpecifications.withFirstName(firstName));
        }
        if (StringUtils.hasText(lastName)) {
            spec = spec.and(SuperFrogStudentSpecifications.withLastName(lastName));
        }
        if (StringUtils.hasText(email)) {
            spec = spec.and(SuperFrogStudentSpecifications.withEmail(email));
        }
        if (StringUtils.hasText(phone)) {
            spec = spec.and(SuperFrogStudentSpecifications.withPhone(phone));
        }
        return superFrogStudentRepository.findAll(spec);
    }

    public SuperFrogStudentDetails getDetails(Integer superFrogStudentId) {
        SuperFrogStudent student = findById(superFrogStudentId);

        List<AppearanceRequest> signedUpAppearances = appearanceRequestRepository.findByAssignedSuperFrogStudentAndAppearanceRequestStatusIn(student, List.of(
                AppearanceRequestStatus.PENDING,
                AppearanceRequestStatus.APPROVED,
                AppearanceRequestStatus.ASSIGNED
        ));

        List<AppearanceRequest> completedAppearances = appearanceRequestRepository.findByAssignedSuperFrogStudentAndAppearanceRequestStatusIn(student, List.of(AppearanceRequestStatus.COMPLETED));

        return new SuperFrogStudentDetails(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhone(),
                signedUpAppearances,
                completedAppearances
        );
    }
}
