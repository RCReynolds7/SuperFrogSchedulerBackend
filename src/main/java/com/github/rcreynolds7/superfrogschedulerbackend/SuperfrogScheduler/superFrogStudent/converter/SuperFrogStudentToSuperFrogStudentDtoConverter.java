package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.converter;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.dto.SuperFrogStudentDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class SuperFrogStudentToSuperFrogStudentDtoConverter  implements Converter<SuperFrogStudent, SuperFrogStudentDto> {

    @Override
    public SuperFrogStudentDto convert(SuperFrogStudent source) {
        return new SuperFrogStudentDto(
                source.getId(),
                source.getFirstName(),
                source.getLastName(),
                source.getEmail(),
                source.getPhone(),
                source.getAddress(),
                source.getActive());
    }
}
