package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SuperFrogStudentServiceTest {
    @Mock
    SuperFrogStudentRepository superFrogStudentRepository;

    @InjectMocks
    SuperFrogStudentService superFrogStudentService;

    List<SuperFrogStudent> superFrogStudents;

    @BeforeEach
    void setUp() {
        SuperFrogStudent s1 = new SuperFrogStudent();
        s1.setId(1);
        s1.setFirstName("tom");
        s1.setLastName("lee");
        s1.setActive(true);
        s1.setPhone("123-456-7901");
        s1.setAddress("2901 Stadium Dr");
        s1.setEmail("tomlee@tcu.edu");

        SuperFrogStudent s2 = new SuperFrogStudent();
        s2.setId(2);
        s2.setFirstName("andre");
        s2.setLastName("gomez");
        s2.setActive(false);
        s2.setPhone("263-456-7891");
        s2.setAddress("3000 McCart Ave");
        s1.setEmail("andregomez@tcu.edu");

        SuperFrogStudent s3 = new SuperFrogStudent();
        s3.setId(3);
        s3.setFirstName("jonny");
        s3.setLastName("long");
        s3.setActive(true);
        s3.setPhone("243-556-7891");
        s3.setAddress("123 Park Ave, OK");
        s3.setEmail("jonny@asu.edu");

        SuperFrogStudent s4 = new SuperFrogStudent();
        s4.setId(4);
        s4.setFirstName("ana");
        s4.setLastName("park");
        s4.setActive(false);
        s4.setPhone("545-522-7491");
        s4.setAddress("Kensington Ave, NY");

        this.superFrogStudents = new ArrayList<>();
        this.superFrogStudents.add(s1);
        this.superFrogStudents.add(s2);
        this.superFrogStudents.add(s3);
        this.superFrogStudents.add(s4);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindByIdSuccess() {
        // Given
        SuperFrogStudent expectedStudent = superFrogStudents.get(0);
        given(superFrogStudentRepository.findById(expectedStudent.getId())).willReturn(Optional.of(expectedStudent));

        // When
        SuperFrogStudent actualStudent = superFrogStudentService.findById(expectedStudent.getId());

        // Then
        assertThat(actualStudent).isEqualTo(expectedStudent);
        verify(superFrogStudentRepository, times(1)).findById(expectedStudent.getId());
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        Integer nonExistentId = 99;
        given(superFrogStudentRepository.findById(nonExistentId)).willReturn(Optional.empty());

        // When
        Throwable thrown = catchThrowable(() -> {
            superFrogStudentService.findById(nonExistentId);
        });

        // Then
        assertThat(thrown).isInstanceOf(ObjectNotFoundException.class);
        verify(superFrogStudentRepository, times(1)).findById(nonExistentId);
    }

    @Test
    void testUpdateSuccess() {
        // Given
        SuperFrogStudent existingStudent = superFrogStudents.get(0);
        SuperFrogStudent updatedInfo = new SuperFrogStudent();
        updatedInfo.setFirstName("UpdatedFirstName");
        updatedInfo.setLastName("UpdatedLastName");

        given(superFrogStudentRepository.findById(1)).willReturn(Optional.of(existingStudent));
        given(superFrogStudentRepository.save(existingStudent)).willReturn(existingStudent);

        // When
        SuperFrogStudent updatedStudent = superFrogStudentService.update(1, updatedInfo);

        // Then
        assertThat(updatedStudent.getFirstName()).isEqualTo("UpdatedFirstName");
        assertThat(updatedStudent.getLastName()).isEqualTo("UpdatedLastName");
        verify(superFrogStudentRepository, times(1)).findById(1);
        verify(superFrogStudentRepository, times(1)).save(existingStudent);
    }

    @Test
    void testUpdateNotFound() {
        // Given
        SuperFrogStudent updatedInfo = new SuperFrogStudent();
        updatedInfo.setFirstName("Nonexistent");
        given(superFrogStudentRepository.findById(anyInt())).willReturn(Optional.empty());

        // When & Then
        assertThrows(ObjectNotFoundException.class, () -> superFrogStudentService.update(99, updatedInfo));
        verify(superFrogStudentRepository, times(1)).findById(99);
    }

    @Test
    void testSearchStudentsSuccess() {
        // Given
        given(superFrogStudentRepository.findAll(any(Specification.class))).willReturn(superFrogStudents.subList(0, 1));

        // When
        List<SuperFrogStudent> foundStudents = superFrogStudentService.searchStudents("tom", null, null, null);

        // Then
        assertThat(foundStudents).hasSize(1);
        assertThat(foundStudents.get(0).getFirstName()).isEqualTo("tom");
    }

    @Test
    void testSearchStudentsNoResults() {
        // Given
        given(superFrogStudentRepository.findAll(any(Specification.class))).willReturn(new ArrayList<>());

        // When
        List<SuperFrogStudent> foundStudents = superFrogStudentService.searchStudents("nonexistent", null, null, null);

        // Then
        assertThat(foundStudents).isEmpty();
    }
}
