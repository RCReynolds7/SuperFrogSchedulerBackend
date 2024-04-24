package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.spiritDirector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequest;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequestService;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.event.Event;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.event.EventService;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.honorarium.dto.HonorariumRequestDto;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.honorarium.dto.HonorariumResponseDto;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.performanceReport.PerformanceReport;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.performanceReport.PerformanceReportRequest;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudentDetails;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudentService;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.StatusCode;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.enums.AppearanceRequestStatus;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.enums.PaymentPreference;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class SpiritDirectorControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    SuperFrogStudentService superFrogStudentService;

    @MockBean
    EventService eventService;

    @MockBean
    AppearanceRequestService appearanceRequestService;

    @Autowired
    ObjectMapper objectMapper;

    List<SuperFrogStudent> superFrogStudents;

    List<Event> events;

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

        Event e1 = new Event();
        e1.setId(1);
        e1.setTitle("Event 1");
        e1.setStartDate(LocalDateTime.parse("2022-12-25T10:30:00"));
        e1.setEndDate(LocalDateTime.parse("2022-12-27T10:50:00"));

        Event e2 = new Event();
        e2.setId(2);
        e2.setTitle("Event 2");
        e2.setStartDate(LocalDateTime.parse("2023-01-03T05:00:00"));
        e2.setEndDate(LocalDateTime.parse("2023-02-03T06:00:00"));
        e2.setRecurring(true);
        e2.setRecurrenceStartDate(LocalDateTime.parse("2023-01-03T05:00:00"));
        e2.setRecurrenceEndDate(LocalDateTime.parse("2023-02-03T06:00:00"));

        this.events = new ArrayList<>();
        this.events.add(e1);
        this.events.add(e2);
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

    @Test
    void testCreateEventSuccess() throws Exception {
        // Given
        Event newEvent = new Event();
        newEvent.setTitle("Event 3");
        newEvent.setStartDate(LocalDateTime.parse("2023-03-25T10:30:00"));
        newEvent.setEndDate(LocalDateTime.parse("2023-03-27T10:50:00"));

        given(this.eventService.createEvent(Mockito.any(Event.class))).willReturn(newEvent);

        // When & Then
        this.mockMvc.perform(post(baseUrl + "/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEvent)))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Event created successfully"))
                .andExpect(jsonPath("$.data.title").value("Event 3"));
    }

    @Test
    void testUpdateEventSuccess() throws Exception {
        // Given
        Event existingEvent = new Event();
        existingEvent.setId(1);
        existingEvent.setTitle("Event 1");
        existingEvent.setStartDate(LocalDateTime.parse("2022-12-25T10:30:00"));
        existingEvent.setEndDate(LocalDateTime.parse("2022-12-27T10:50:00"));

        Event updatedEvent = new Event();
        updatedEvent.setId(1);
        updatedEvent.setTitle("Updated Title");
        updatedEvent.setStartDate(LocalDateTime.parse("2022-12-25T10:30:00"));
        updatedEvent.setEndDate(LocalDateTime.parse("2022-12-27T11:40:00"));

        given(this.eventService.findById(1)).willReturn(existingEvent);
        given(this.eventService.updateEvent(1, existingEvent)).willReturn(updatedEvent);

        // When & Then
        this.mockMvc.perform(put(baseUrl + "/events/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(existingEvent)))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Event updated successfully"))
                .andExpect(jsonPath("$.data.title").value("Updated Title"))
                .andExpect(jsonPath("$.data.startDate").value("2022-12-25 10:30:00"))
                .andExpect(jsonPath("$.data.endDate").value("2022-12-27 11:40:00"));
    }

    @Test
    void testDeleteEventSuccess() throws Exception {
        // Given
        doNothing().when(this.eventService).deleteEvent(1);

        // When & Then
        this.mockMvc.perform(delete(baseUrl + "/events/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Event deleted successfully"));
    }

    @Test
    void testDeleteEventWithNonExistentId() throws Exception {
        // Given
        doThrow(new ObjectNotFoundException("Event", 5)).when(this.eventService).deleteEvent(5);

        // When & Then
        this.mockMvc.perform(delete(baseUrl + "/events/5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find Event with Id 5 :("));
    }

    @Test
    void testCreateHonorariumSuccess() throws Exception {
        // Given
        Integer superFrogStudentId = 1;
        LocalDateTime startDate = LocalDateTime.of(2023, 4, 15, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 4, 20, 10, 0);
        HonorariumRequestDto honorariumRequest = new HonorariumRequestDto(superFrogStudentId,startDate, endDate);
        SuperFrogStudent student = new SuperFrogStudent();
        student.setId(superFrogStudentId);
        student.setPaymentPreference(PaymentPreference.MAIL_CHECK);
        student.setInternational(false);
        student.setAddress("123 Main St");

        AppearanceRequest completedRequest = new AppearanceRequest();
        completedRequest.setAppearanceRequestStatus(AppearanceRequestStatus.COMPLETED);
        List<AppearanceRequest> completedRequests = List.of(completedRequest);

        HonorariumResponseDto responseDto = new HonorariumResponseDto(
                superFrogStudentId,
                student.getPaymentPreference(),
                student.getInternational(),
                student.getAddress(),
                10 // Assuming one completed request and $10 per request
        );

        given(superFrogStudentService.findById(superFrogStudentId)).willReturn(student);
        given(appearanceRequestService.findCompletedBySuperFrogStudentIdAndDateRange(student, startDate, endDate))
                .willReturn(completedRequests);
        doNothing().when(appearanceRequestService).updateStatusToSubmittedToPayroll(completedRequests);

        // When & Then
        mockMvc.perform(post(baseUrl + "/create-honorarium/{superFrogStudentId}", superFrogStudentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(honorariumRequest)))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Honorarium requests created and submitted to payroll successfully."))
                .andExpect(jsonPath("$.data.superFrogStudentId").value(superFrogStudentId))
                .andExpect(jsonPath("$.data.amount").value(10));
    }

    @Test
    void testCreateHonorariumNoCompletedRequests() throws Exception {
        // Given
        Integer superFrogStudentId = 1;
        LocalDateTime startDate = LocalDateTime.of(2023, 4, 15, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 4, 20, 10, 0);
        HonorariumRequestDto honorariumRequest = new HonorariumRequestDto(superFrogStudentId, startDate, endDate);
        SuperFrogStudent student = new SuperFrogStudent();
        student.setId(superFrogStudentId);

        given(superFrogStudentService.findById(superFrogStudentId)).willReturn(student);
        given(appearanceRequestService.findCompletedBySuperFrogStudentIdAndDateRange(student, startDate, endDate))
                .willReturn(new ArrayList<>());

        // When & Then
        mockMvc.perform(post(baseUrl + "/create-honorarium/{superFrogStudentId}", superFrogStudentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(honorariumRequest)))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("No completed appearance requests found for this SuperFrog Student."));
    }

    @Test
    void testCreatePerformanceReportSuccess() throws Exception {
        // Given
        int superFrogStudentId = 1;
        LocalDateTime startDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 3, 31, 23, 59);
        PerformanceReportRequest request = new PerformanceReportRequest();
        request.setStartDate(startDate);
        request.setEndDate(endDate);

        SuperFrogStudent student = new SuperFrogStudent();
        student.setId(superFrogStudentId);
        student.setFirstName("John");
        student.setLastName("Doe");

        AppearanceRequest completedRequest = new AppearanceRequest();
        completedRequest.setAppearanceRequestStatus(AppearanceRequestStatus.COMPLETED);
        completedRequest.setAssignedSuperFrogStudent(student);

        AppearanceRequest cancelledRequest = new AppearanceRequest();
        cancelledRequest.setAppearanceRequestStatus(AppearanceRequestStatus.CANCELED_BY_THE_SPIRIT_DIRECTOR);
        cancelledRequest.setAssignedSuperFrogStudent(student);

        List<AppearanceRequest> appearances = List.of(completedRequest, cancelledRequest);

        PerformanceReport expectedReport = new PerformanceReport();
        expectedReport.setSuperFrogStudentId(superFrogStudentId);
        expectedReport.setSuperFrogStudentFirstName("John");
        expectedReport.setSuperFrogStudentLastName("Doe");
        expectedReport.setStartDate(startDate);
        expectedReport.setEndDate(endDate);
        expectedReport.setCompletedAppearances(1);
        expectedReport.setCancelledAppearances(1);

        given(superFrogStudentService.findById(superFrogStudentId)).willReturn(student);
        given(appearanceRequestService.findCompletedBySuperFrogStudentIdAndDateRange(student, startDate, endDate))
                .willReturn(appearances);
        given(superFrogStudentService.generatePerformanceReport(superFrogStudentId, startDate, endDate))
                .willReturn(expectedReport);

        // When & Then
        mockMvc.perform(post(baseUrl + "/create-performance-report/{superFrogStudentId}", superFrogStudentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Performance report generated successfully."))
                .andExpect(jsonPath("$.data.superFrogStudentId").value(superFrogStudentId))
                .andExpect(jsonPath("$.data.superFrogStudentFirstName").value("John"))
                .andExpect(jsonPath("$.data.superFrogStudentLastName").value("Doe"))
                .andExpect(jsonPath("$.data.completedAppearances").value(1))
                .andExpect(jsonPath("$.data.cancelledAppearances").value(1));
    }
}
