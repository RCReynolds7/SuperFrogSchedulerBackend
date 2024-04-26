package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.enums.AppearanceRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface AppearanceRequestRepository extends JpaRepository<AppearanceRequest, Integer>, JpaSpecificationExecutor<AppearanceRequest> {

    List<AppearanceRequest> findByStatus(AppearanceRequestStatus status);
    List<AppearanceRequest> findByStatusAndStudent(AppearanceRequestStatus status, SuperFrogStudent student);

    List<AppearanceRequest> findByAssignedSuperFrogStudentAndAppearanceRequestStatusIn(SuperFrogStudent student, List<AppearanceRequestStatus> statuses);
    List<AppearanceRequest> findByAssignedSuperFrogStudentAndAppearanceRequestStatusInAndDateBetween(
            SuperFrogStudent student,
            List<AppearanceRequestStatus> statuses,
            LocalDateTime startDate,
            LocalDateTime endDate
    );
}