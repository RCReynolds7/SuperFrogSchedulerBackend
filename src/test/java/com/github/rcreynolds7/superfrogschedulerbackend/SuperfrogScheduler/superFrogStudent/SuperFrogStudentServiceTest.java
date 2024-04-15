package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.enums.PaymentPreference;
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
import static org.mockito.BDDMockito.willThrow;
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
        s1.setPhone("(123) 456-7901");
        s1.setAddress("2901 Stadium Dr, Fort Worth, TX 76109");
        s1.setEmail("tomlee@tcu.edu");

        SuperFrogStudent s2 = new SuperFrogStudent();
        s2.setId(2);
        s2.setFirstName("andre");
        s2.setLastName("gomez");
        s2.setActive(false);
        s2.setPhone("(263) 456-7891");
        s2.setAddress("3000 McCart Ave, Fort Worth, TX 76133");
        s2.setEmail("andregomez@tcu.edu");

        SuperFrogStudent s3 = new SuperFrogStudent();
        s3.setId(3);
        s3.setFirstName("jonny");
        s3.setLastName("long");
        s3.setActive(true);
        s3.setPhone("(243) 556-7891");
        s3.setAddress("123 Park Ave, Oklahoma City, OK 73102");
        s3.setEmail("jonny@asu.edu");

        SuperFrogStudent s4 = new SuperFrogStudent();
        s4.setId(4);
        s4.setFirstName("ana");
        s4.setLastName("park");
        s4.setActive(false);
        s4.setPhone("(545) 522-7491");
        s4.setAddress("123 Kensington Ave, Brooklyn, NY 11218");
        s4.setEmail("ana@gmail.com");

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
    void testUpdateSuccess1() {
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
    void testUpdateSuccess2() {
        // Given
        SuperFrogStudent existingStudent = superFrogStudents.get(2);
        SuperFrogStudent updatedInfo = new SuperFrogStudent();
        updatedInfo.setFirstName("UpdatedFirstName");
        updatedInfo.setLastName("UpdatedLastName");
        updatedInfo.setEmail("updatedEmail@tcu.edu");
        updatedInfo.setPhone("999-999-9999");
        updatedInfo.setAddress("999 Updated Address Dr");
        updatedInfo.setActive(false);
        updatedInfo.setInternational(true);
        updatedInfo.setPaymentPreference(PaymentPreference.PICK_UP_CHECK);

        given(superFrogStudentRepository.findById(2)).willReturn(Optional.of(existingStudent));
        given(superFrogStudentRepository.save(existingStudent)).willReturn(existingStudent);

        // When
        SuperFrogStudent updatedStudent = superFrogStudentService.update(2, updatedInfo);

        // Then
        assertThat(updatedStudent.getFirstName()).isEqualTo("UpdatedFirstName");
        assertThat(updatedStudent.getLastName()).isEqualTo("UpdatedLastName");
        assertThat(updatedStudent.getEmail()).isEqualTo("updatedEmail@tcu.edu");
        assertThat(updatedStudent.getPhone()).isEqualTo("999-999-9999");
        assertThat(updatedStudent.getAddress()).isEqualTo("999 Updated Address Dr");
        assertThat(updatedStudent.getActive()).isEqualTo(false);
        assertThat(updatedStudent.getInternational()).isEqualTo(true);
        assertThat(updatedStudent.getPaymentPreference()).isEqualTo(PaymentPreference.PICK_UP_CHECK);

        verify(superFrogStudentRepository, times(1)).findById(2);
        verify(superFrogStudentRepository, times(1)).save(any(SuperFrogStudent.class));
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
    void testUpdateInvalidFormat() {
        // Given
        Integer studentId = 1;
        SuperFrogStudent existingStudent = superFrogStudents.get(0); // Assuming the first student is the one we're trying to update.
        SuperFrogStudent invalidUpdateInfo = new SuperFrogStudent();
        invalidUpdateInfo.setEmail("invalidEmail"); // Invalid email format
        invalidUpdateInfo.setPhone("1234567890"); // Invalid phone format
        invalidUpdateInfo.setAddress("Invalid Address Format"); // Invalid address format

        given(superFrogStudentRepository.findById(studentId)).willReturn(Optional.of(existingStudent));

        // Assuming save method or some validation method throws IllegalArgumentException for invalid formats
        willThrow(new IllegalArgumentException("Invalid data format")).given(superFrogStudentRepository).save(any(SuperFrogStudent.class));

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> superFrogStudentService.update(studentId, invalidUpdateInfo));
        verify(superFrogStudentRepository, times(1)).findById(studentId);
        verify(superFrogStudentRepository, times(1)).save(any(SuperFrogStudent.class)); // Verifies save was attempted and exception was thrown
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
