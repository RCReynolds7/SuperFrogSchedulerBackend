package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        s1.setPhone("12345678901");
        s1.setProfileInfo("Student tom lee");
        s1.setEmail("tomlee@tcu.edu");

        SuperFrogStudent s2 = new SuperFrogStudent();
        s1.setId(2);
        s1.setFirstName("andre");
        s1.setLastName("gomez");
        s1.setActive(false);
        s1.setPhone("26345678901");
        s1.setProfileInfo("Student andre gomez");
        s1.setEmail("andregomez@tcu.edu");

        this.superFrogStudents = new ArrayList<>();
        this.superFrogStudents.add(s1);
        this.superFrogStudents.add(s2);
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
        assertThat(thrown).isInstanceOf(SuperFrogStudentNotFoundException.class);
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
        assertThrows(SuperFrogStudentNotFoundException.class, () -> superFrogStudentService.update(99, updatedInfo));
        verify(superFrogStudentRepository, times(1)).findById(99);
    }
}
