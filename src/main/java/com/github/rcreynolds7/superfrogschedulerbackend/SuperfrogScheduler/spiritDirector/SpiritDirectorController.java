package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.spiritDirector;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequest;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequestService;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.dto.AppearanceRequestDto;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.event.Event;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.event.EventService;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.honorarium.dto.HonorariumRequestDto;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.honorarium.dto.HonorariumResponseDto;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.performanceReport.PerformanceReport;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.performanceReport.PerformanceReportRequest;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudentDetails;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudentService;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.dto.SuperFrogStudentDto;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.Result;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/spirit-directors")
public class SpiritDirectorController {
    private final SuperFrogStudentService superFrogStudentService;
    private final EventService eventService;
    private final AppearanceRequestService appearanceRequestService;

    public SpiritDirectorController(SuperFrogStudentService superFrogStudentService, EventService eventService, AppearanceRequestService appearanceRequestService) {
        this.superFrogStudentService = superFrogStudentService;
        this.eventService = eventService;
        this.appearanceRequestService = appearanceRequestService;
    }

    @PutMapping("/superfrog-students/{superFrogStudentId}/deactivate")
    public Result deactivateSuperFrogStudent(@PathVariable Integer superFrogStudentId) {
        // Fetch the student to deactivate
        SuperFrogStudent superFrogStudent = superFrogStudentService.findById(superFrogStudentId);

        superFrogStudent.setActive(false);
        superFrogStudentService.update(superFrogStudentId, superFrogStudent);

        return new Result(true, StatusCode.SUCCESS, "SuperFrog Student deactivated successfully");
    }

    @GetMapping("/superfrog-students/search")
    public Result searchSuperFrogStudents(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone) {
        // Check if all search parameters are empty
        if (!StringUtils.hasText(firstName) && !StringUtils.hasText(lastName)
                && !StringUtils.hasText(email) && !StringUtils.hasText(phone)) {
            return new Result(false, StatusCode.INVALID_ARGUMENT, "At least one search parameter must be provided");
        }

        List<SuperFrogStudent> students = superFrogStudentService.searchStudents(firstName, lastName, email, phone);
        List<SuperFrogStudentDto> resultDtos = students.stream()
                .map(student -> new SuperFrogStudentDto(
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getAddress(),
                        student.getActive()))
                .collect(Collectors.toList());

        return new Result(true, StatusCode.SUCCESS, "SuperFrog student search completed successfully", resultDtos);
    }

    @GetMapping("/superfrog-students")
    public Result getAllSuperFrogStudents() {
        List<SuperFrogStudent> students = superFrogStudentService.findAll();
        List<SuperFrogStudentDto> resultDtos = students.stream()
                .map(student -> new SuperFrogStudentDto(
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getAddress(),
                        student.getActive()))
                .collect(Collectors.toList());

        return new Result(true, StatusCode.SUCCESS, "SuperFrog students retrieved successfully", resultDtos);
    }

    @GetMapping("/superfrog-students/{superFrogStudentId}/details")
    public Result getSuperFrogStudentDetails(@PathVariable Integer superFrogStudentId) {
        SuperFrogStudentDetails student = superFrogStudentService.getDetails(superFrogStudentId);

        return new Result(true, StatusCode.SUCCESS, "SuperFrog student details retrieved successfully", student);
    }

    @PostMapping("/events")
    public Result createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return new Result(true, StatusCode.SUCCESS, "Event created successfully", createdEvent);
    }

    @PutMapping("/events/{eventId}")
    public Result updateEvent(@PathVariable Integer eventId, @RequestBody Event eventDetails) {
        Event event = eventService.findById(eventId);

        Optional.ofNullable(eventDetails.getTitle())
                .ifPresent(event::setTitle);

        Optional.ofNullable(eventDetails.getStartDate())
                .ifPresent(event::setStartDate);

        Optional.ofNullable(eventDetails.getEndDate())
                .ifPresent(event::setEndDate);

        Optional.ofNullable(eventDetails.getRecurrenceStartDate())
                .ifPresent(event::setRecurrenceStartDate);

        Optional.ofNullable(eventDetails.getRecurrenceEndDate())
                .ifPresent(event::setRecurrenceEndDate);

        Event updatedEvent = eventService.updateEvent(eventId, event);

        // Update the entity in the database
        return new Result(true, StatusCode.SUCCESS, "Event updated successfully", updatedEvent);
    }

    @DeleteMapping("/events/{eventId}")
    public Result deleteEvent(@PathVariable Integer eventId) {
        this.eventService.deleteEvent(eventId);
        return new Result(true, StatusCode.SUCCESS, "Event deleted successfully");
    }

    @PostMapping ("/create-honorarium/{superFrogStudentId}")
    public Result createHonorarium(@PathVariable Integer superFrogStudentId, @RequestBody HonorariumRequestDto honorariumRequest) {
        LocalDateTime startDate = honorariumRequest.startDate();
        LocalDateTime endDate = honorariumRequest.endDate();

        SuperFrogStudent superFrogStudent = superFrogStudentService.findById(superFrogStudentId);
        List<AppearanceRequest> completedRequests = appearanceRequestService.findCompletedBySuperFrogStudentIdAndDateRange(superFrogStudent, startDate, endDate);

        if (completedRequests.isEmpty()) {
            return new Result(false, StatusCode.NOT_FOUND, "No completed appearance requests found for this SuperFrog Student.");
        }

        appearanceRequestService.updateStatusToSubmittedToPayroll(completedRequests);

        // Calculate the amount
        int amount = completedRequests.size() * 10;

        // Create the response DTO
        HonorariumResponseDto responseDto = new HonorariumResponseDto(
                superFrogStudent.getId(),
                superFrogStudent.getPaymentPreference(),
                superFrogStudent.getInternational(),
                superFrogStudent.getAddress(),
                amount
        );

        return new Result(true, StatusCode.SUCCESS, "Honorarium requests created and submitted to payroll successfully.", responseDto);
    }

    @PostMapping("/create-performance-report/{superFrogStudentId}")
    public Result createPerformanceReport(@PathVariable Integer superFrogStudentId, @RequestBody PerformanceReportRequest request) {
        PerformanceReport report = superFrogStudentService.generatePerformanceReport(superFrogStudentId, request.getStartDate(), request.getEndDate());
        return new Result(true, StatusCode.SUCCESS, "Performance report generated successfully.", report);
    }

}