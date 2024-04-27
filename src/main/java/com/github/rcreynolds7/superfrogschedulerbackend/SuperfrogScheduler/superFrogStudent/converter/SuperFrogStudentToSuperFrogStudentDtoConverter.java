package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.converter;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.dto.SuperFrogStudentDto;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class SuperFrogStudentToSuperFrogStudentDtoConverter implements Converter<SuperFrogStudent, SuperFrogStudentDto> {

    public final SuperFrogStudentToSuperFrogStudentDtoConverter superFrogStudentToSuperFrogStudentDtoConverter;
    public SuperFrogStudentToSuperFrogStudentDtoConverter(SuperFrogStudentToSuperFrogStudentDtoConverter superFrogStudentToSuperFrogStudentDtoConverter) {
        this.superFrogStudentToSuperFrogStudentDtoConverter = superFrogStudentToSuperFrogStudentDtoConverter;
    }

    @Override
    public SuperFrogStudentDto convert(SuperFrogStudent source) {
        SuperFrogStudentDto superFrogStudentDto = new SuperFrogStudentDto(source.getId(),
                source.getFirstName(),
                source.getLastName(),
                source.getEmail(),
                source.getPhone(),
                source.getAddress(),
                source.getActive());
        return superFrogStudentDto;
    }
}