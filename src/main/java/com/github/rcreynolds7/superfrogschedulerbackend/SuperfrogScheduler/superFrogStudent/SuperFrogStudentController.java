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

    @PutMapping("/{id}")
    public Result updateSuperFrogStudent(@PathVariable Integer id, @RequestBody SuperFrogStudent superFrogStudentUpdate) {
        SuperFrogStudent updatedSuperFrogStudent = superFrogStudentService.update(id, superFrogStudentUpdate);
        return new Result(true, StatusCode.SUCCESS, "SuperFrog Student information updated successfully");
    }
}
