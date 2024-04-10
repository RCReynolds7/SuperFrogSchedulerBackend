package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.Result;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.StatusCode;
import org.springframework.web.bind.annotation.*;


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

        if (superFrogStudentUpdate.getFirstName() != null && !superFrogStudentUpdate.getFirstName().isEmpty()) {
            superFrogStudent.setFirstName(superFrogStudentUpdate.getFirstName());
        }

        if (superFrogStudentUpdate.getLastName() != null && !superFrogStudentUpdate.getLastName().isEmpty()) {
            superFrogStudent.setLastName(superFrogStudentUpdate.getLastName());
        }

        if (superFrogStudentUpdate.getEmail() != null && !superFrogStudentUpdate.getEmail().isEmpty()) {
            superFrogStudent.setEmail(superFrogStudentUpdate.getEmail());
        }

        if (superFrogStudentUpdate.getPhone() != null && !superFrogStudentUpdate.getPhone().isEmpty()) {
            superFrogStudent.setPhone(superFrogStudentUpdate.getPhone());
        }

        if (superFrogStudentUpdate.getAddress() != null && !superFrogStudentUpdate.getAddress().isEmpty()) {
            superFrogStudent.setAddress(superFrogStudentUpdate.getAddress());
        }

        if (superFrogStudentUpdate.getActive() != null) {
            superFrogStudent.setActive(superFrogStudentUpdate.getActive());
        }

        if (superFrogStudentUpdate.getInternational() != null) {
            superFrogStudent.setInternational(superFrogStudentUpdate.getInternational());
        }

        if (superFrogStudentUpdate.getPaymentPreference() != null) {
            superFrogStudent.setPaymentPreference(superFrogStudentUpdate.getPaymentPreference());
        }

        // Update the entity in the database
        superFrogStudentService.update(superFrogStudentId, superFrogStudent);


        return new Result(true, StatusCode.SUCCESS, "SuperFrog Student information updated successfully");
    }
}
