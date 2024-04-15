package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.spiritDirector;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.event.Event;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.event.EventService;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudentDetails;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudentService;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.dto.SuperFrogStudentDto;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.Result;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/spirit-directors")
public class SpiritDirectorController {
    private final SuperFrogStudentService superFrogStudentService;
    private final EventService eventService;

    public SpiritDirectorController(SuperFrogStudentService superFrogStudentService, EventService eventService) {
        this.superFrogStudentService = superFrogStudentService;
        this.eventService = eventService;
    }

    @PutMapping("superfrog-students/{superFrogStudentId}/deactivate")
    public Result deactivateSuperFrogStudent(@PathVariable Integer superFrogStudentId) {
        // Fetch the student to deactivate
        SuperFrogStudent superFrogStudent = superFrogStudentService.findById(superFrogStudentId);

        superFrogStudent.setActive(false);
        superFrogStudentService.update(superFrogStudentId, superFrogStudent);

        return new Result(true, StatusCode.SUCCESS, "SuperFrog Student deactivated successfully");
    }

    @GetMapping("superfrog-students/search")
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
                        student.getId().toString(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getAddress(),
                        student.getActive()))
                .collect(Collectors.toList());

        return new Result(true, StatusCode.SUCCESS, "SuperFrog student search completed successfully", resultDtos);
    }

    @GetMapping("superfrog-students/{superFrogStudentId}/details")
    public Result getSuperFrogStudentDetails(@PathVariable Integer superFrogStudentId) {
        SuperFrogStudentDetails student = superFrogStudentService.getDetails(superFrogStudentId);

        return new Result(true, StatusCode.SUCCESS, "SuperFrog student details retrieved successfully", student);
    }

    @PostMapping("/events")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @PutMapping("/events/{eventId}")
    public Event updateEvent(@PathVariable Integer eventId, @RequestBody Event eventDetails) {
        Event event = eventService.findById(eventId);

        Optional.ofNullable(eventDetails.getTitle())
                .filter(title -> !title.isEmpty())
                .ifPresent(event::setTitle);

        Optional.ofNullable(eventDetails.getStartDate())
                .ifPresent(event::setStartDate);

        Optional.ofNullable(eventDetails.getEndDate())
                .ifPresent(event::setEndDate);

        Optional.ofNullable(eventDetails.getRecurrenceStartDate())
                .ifPresent(event::setRecurrenceStartDate);

        Optional.ofNullable(eventDetails.getRecurrenceEndDate())
                .ifPresent(event::setRecurrenceEndDate);

        // Update the entity in the database
        return eventService.updateEvent(eventId, event);
    }

    @DeleteMapping("/events/{eventId}")
    public void deleteEvent(@PathVariable Integer eventId) {
        eventService.deleteEvent(eventId);
    }
}
