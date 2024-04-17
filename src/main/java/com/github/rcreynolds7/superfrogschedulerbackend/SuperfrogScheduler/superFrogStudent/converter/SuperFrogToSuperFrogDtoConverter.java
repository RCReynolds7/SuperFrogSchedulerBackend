package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.converter;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.dto.SuperFrogStudentDto;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class SuperFrogToSuperFrogDtoConverter  implements Converter<SuperFrogStudent, SuperFrogStudentDto> {

    public SuperFrogToSuperFrogDtoConverter() {
    }

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

}