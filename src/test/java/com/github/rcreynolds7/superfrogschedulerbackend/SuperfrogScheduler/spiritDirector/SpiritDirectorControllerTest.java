package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.spiritDirector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequest;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudentDetails;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudentService;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.StatusCode;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.enums.AppearanceRequestStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class SpiritDirectorControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    SuperFrogStudentService superFrogStudentService;

    @Autowired
    ObjectMapper objectMapper;

    List<SuperFrogStudent> superFrogStudents;

    @Value("${api.endpoint.base-url}/spirit-directors")
    String baseUrl;

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

    @AfterEach()
    void tearDown() {
    }

    @Test
    void testDeactivateSuperFrogStudentSuccess() throws Exception {
        // Given
        SuperFrogStudent existingStudent = new SuperFrogStudent();
        existingStudent.setId(1);
        existingStudent.setActive(true);

        given(superFrogStudentService.findById(eq(1))).willReturn(existingStudent);
        given(superFrogStudentService.update(eq(1), any(SuperFrogStudent.class))).willReturn(existingStudent);

        // When & Then
        mockMvc.perform(put( this.baseUrl + "/superfrog-students/{superFrogStudentId}/deactivate", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("SuperFrog Student deactivated successfully"));
    }

    @Test
    void testSearchSuperFrogStudentsSuccess() throws Exception {
        // Given
        given(superFrogStudentService.searchStudents("tom", null, null, null)).willReturn(superFrogStudents.subList(0, 1));

        // When & Then
        mockMvc.perform(get(this.baseUrl + "/superfrog-students/search")
                        .param("firstName", "tom")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("SuperFrog student search completed successfully"))
                .andExpect(jsonPath("$.data[0].firstName").value("tom"));
    }

    @Test
    void testSearchSuperFrogStudentsNoResults() throws Exception {
        // Given
        given(superFrogStudentService.searchStudents("nonexistent", null, null, null)).willReturn(new ArrayList<>());

        // When & Then
        mockMvc.perform(get(this.baseUrl + "/superfrog-students/search")
                        .param("firstName", "nonexistent")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("SuperFrog student search completed successfully"))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testSearchSuperFrogStudentsWithNoParameters() throws Exception {
        // Attempt to perform search without providing any search parameters
        mockMvc.perform(get(this.baseUrl + "/superfrog-students/search")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.INVALID_ARGUMENT))
                .andExpect(jsonPath("$.message").value("At least one search parameter must be provided"));
    }

    @Test
    void getSuperFrogStudentDetailsSuccess() throws Exception {
        // Given
        SuperFrogStudent student = new SuperFrogStudent();
        student.setId(1);
        student.setFirstName("Tom");
        student.setLastName("Lee");
        student.setEmail("tom@example.com");
        student.setPhone("(123) 456-7890");

        AppearanceRequest request1 = new AppearanceRequest();
        request1.setAppearanceRequestStatus(AppearanceRequestStatus.PENDING);
        request1.setAssignedSuperFrogStudent(student);

        AppearanceRequest request2 = new AppearanceRequest();
        request2.setAppearanceRequestStatus(AppearanceRequestStatus.APPROVED);
        request2.setAssignedSuperFrogStudent(student);

        AppearanceRequest request3 = new AppearanceRequest();
        request3.setAppearanceRequestStatus(AppearanceRequestStatus.COMPLETED);
        request3.setAssignedSuperFrogStudent(student);

        List<AppearanceRequest> signedUpAppearances = List.of(request1, request2);
        List<AppearanceRequest> completedAppearances = List.of(request3);

        SuperFrogStudentDetails expectedDetails = new SuperFrogStudentDetails(
                "Tom",
                "Lee",
                "tom@example.com",
                "(123) 456-7890",
                signedUpAppearances,
                completedAppearances
        );

        given(superFrogStudentService.findById(1)).willReturn(student);
        given(superFrogStudentService.getDetails(1)).willReturn(expectedDetails);

        // When & Then
        mockMvc.perform(get(baseUrl + "/superfrog-students/{superFrogStudentId}/details", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("SuperFrog student details retrieved successfully"))
                .andExpect(jsonPath("$.data.firstName").value("Tom"))
                .andExpect(jsonPath("$.data.lastName").value("Lee"))
                .andExpect(jsonPath("$.data.email").value("tom@example.com"))
                .andExpect(jsonPath("$.data.phone").value("(123) 456-7890"))
                .andExpect(jsonPath("$.data.signedUpAppearances.length()").value(2))
                .andExpect(jsonPath("$.data.completedAppearances.length()").value(1));
    }


    @Test
    void getSuperFrogStudentDetailsWithNoParameters() throws Exception {
        // When & Then
        mockMvc.perform(get(baseUrl + "/superfrog-students/1/details")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("SuperFrog student details retrieved successfully"))
                .andExpect(jsonPath("$.data").isEmpty());
    }
}
