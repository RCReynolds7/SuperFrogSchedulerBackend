package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.dto.SuperFrogStudentDto;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.Result;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.StatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Optional;


@RestController
@RequestMapping("${api.endpoint.base-url}/superfrog-students")
public class SuperFrogStudentController {
    private final SuperFrogStudentService superFrogStudentService;

    public SuperFrogStudentController(SuperFrogStudentService superFrogStudentService) {
        this.superFrogStudentService = superFrogStudentService;
    }

    @PutMapping("/{superFrogStudentId}")
    public Result updateSuperFrogStudent(@PathVariable Integer superFrogStudentId, @RequestBody SuperFrogStudent superFrogStudentUpdate) {
        SuperFrogStudent superFrogStudent = superFrogStudentService.findById(superFrogStudentId);

        Optional.ofNullable(superFrogStudentUpdate.getFirstName())
                .filter(name -> !name.isEmpty())
                .ifPresent(superFrogStudent::setFirstName);

        Optional.ofNullable(superFrogStudentUpdate.getLastName())
                .filter(name -> !name.isEmpty())
                .ifPresent(superFrogStudent::setLastName);

        Optional.ofNullable(superFrogStudentUpdate.getEmail())
                .filter(email -> !email.isEmpty())
                .ifPresent(superFrogStudent::setEmail);

        Optional.ofNullable(superFrogStudentUpdate.getPhone())
                .filter(phone -> !phone.isEmpty())
                .ifPresent(superFrogStudent::setPhone);

        Optional.ofNullable(superFrogStudentUpdate.getAddress())
                .filter(address -> !address.isEmpty())
                .ifPresent(superFrogStudent::setAddress);

        Optional.ofNullable(superFrogStudentUpdate.getActive())
                .ifPresent(superFrogStudent::setActive);

        Optional.ofNullable(superFrogStudentUpdate.getInternational())
                .ifPresent(superFrogStudent::setInternational);

        Optional.ofNullable(superFrogStudentUpdate.getPaymentPreference())
                .ifPresent(superFrogStudent::setPaymentPreference);

        // Update the entity in the database
        superFrogStudentService.update(superFrogStudentId, superFrogStudent);


        return new Result(true, StatusCode.SUCCESS, "SuperFrog Student information updated successfully");
    }
    @PostMapping("/{superfrog-students}")
    public ResponseEntity<?> createSuperFrogStudent(@RequestBody SuperFrogStudentDto studentDto) {
        try {
            SuperFrogStudent createdStudent = superFrogStudentService.createSuperFrogStudent(studentDto);
            return ResponseEntity.ok(createdStudent);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: " + e.getMessage());
        }
    }

}
