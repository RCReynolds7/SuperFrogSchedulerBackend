package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.converter.SuperFrogStudentDtoToSuperFrogStudentConverter;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.converter.SuperFrogStudentToSuperFrogStudentDtoConverter;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.dto.SuperFrogStudentDto;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.Result;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("${api.endpoint.base-url}/superfrog-students")
public class SuperFrogStudentController {
    private final SuperFrogStudentService superFrogStudentService;
    private final SuperFrogStudentToSuperFrogStudentDtoConverter superFrogStudentToSuperFrogStudentDtoConverter;

    private final SuperFrogStudentDtoToSuperFrogStudentConverter superFrogStudentDtoToSuperFrogStudentConverter;


    public SuperFrogStudentController(SuperFrogStudentService superFrogStudentService, SuperFrogStudentDtoToSuperFrogStudentConverter superFrogStudentDtoToSuperFrogStudentConverter, SuperFrogStudentToSuperFrogStudentDtoConverter superFrogStudentToSuperFrogStudentDtoConverter) {
        this.superFrogStudentService = superFrogStudentService;
        this.superFrogStudentToSuperFrogStudentDtoConverter = superFrogStudentToSuperFrogStudentDtoConverter;
        this.superFrogStudentDtoToSuperFrogStudentConverter = superFrogStudentDtoToSuperFrogStudentConverter;

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
   
    @PostMapping("/") 
    public Result createSuperFrogStudent(@Valid @RequestBody SuperFrogStudentDto studentDto){
        SuperFrogStudent newStudent = this.superFrogStudentDtoToSuperFrogStudentConverter.convert(studentDto);
        SuperFrogStudent savedStudent = this.superFrogStudentService.save(newStudent);
        SuperFrogStudentDto savedStudentDto = this.superFrogStudentToSuperFrogStudentDtoConverter.convert(savedStudent);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedStudentDto);
    }


}
