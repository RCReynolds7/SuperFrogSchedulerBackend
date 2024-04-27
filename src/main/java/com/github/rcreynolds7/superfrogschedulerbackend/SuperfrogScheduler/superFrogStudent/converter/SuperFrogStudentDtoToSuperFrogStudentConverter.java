package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.converter;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.dto.SuperFrogStudentDto;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class SuperFrogStudentDtoToSuperFrogStudentConverter implements Converter<SuperFrogStudentDto, SuperFrogStudent> {
    @Override
    public SuperFrogStudent convert(SuperFrogStudentDto source) {
        SuperFrogStudent superFrogStudent = new SuperFrogStudent();
        superFrogStudent.setId(Integer.valueOf(source.id()));
        superFrogStudent.setFirstName(source.firstName());
        superFrogStudent.setLastName(source.lastName());
        superFrogStudent.setEmail(source.email());
        superFrogStudent.setAddress(source.address());
        superFrogStudent.setPhone(source.phone());
        superFrogStudent.setActive(source.isActive());
        return superFrogStudent;
    }

}