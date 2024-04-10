package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.endpoint.base-url}/superfrog-students")
public class SuperFrogStudentController {
    private final SuperFrogStudentService superFrogStudentService;

    public SuperFrogStudentController(SuperFrogStudentService superFrogStudentService) {
        this.superFrogStudentService = superFrogStudentService;
    }
}
