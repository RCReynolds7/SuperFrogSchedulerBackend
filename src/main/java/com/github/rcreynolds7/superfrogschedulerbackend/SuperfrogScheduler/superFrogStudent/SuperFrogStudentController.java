package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.Result;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.StatusCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/superfrog-students")
public class SuperFrogStudentController {
    private final SuperFrogStudentService superFrogStudentService;

    public SuperFrogStudentController(SuperFrogStudentService superFrogStudentService) {
        this.superFrogStudentService = superFrogStudentService;
    }

    @PutMapping("/{superFrogStudentId}/deactivate")
    public Result deactivateSuperFrogStudent(@PathVariable Integer superFrogStudentId) {
            // Fetch the student to deactivate
            SuperFrogStudent superFrogStudent = superFrogStudentService.findById(superFrogStudentId);

            superFrogStudent.setActive(false);
            superFrogStudentService.update(superFrogStudentId, superFrogStudent);

            return new Result(true, StatusCode.SUCCESS, "SuperFrog Student deactivated successfully");
    }
}
