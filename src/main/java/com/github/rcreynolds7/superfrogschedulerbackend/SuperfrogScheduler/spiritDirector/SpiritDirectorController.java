package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.spiritDirector;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudentService;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.dto.SuperFrogStudentDto;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.Result;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/spirit-directors")
public class SpiritDirectorController {
    private final SuperFrogStudentService superFrogStudentService;

    public SpiritDirectorController(SuperFrogStudentService superFrogStudentService) {
        this.superFrogStudentService = superFrogStudentService;
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
}
