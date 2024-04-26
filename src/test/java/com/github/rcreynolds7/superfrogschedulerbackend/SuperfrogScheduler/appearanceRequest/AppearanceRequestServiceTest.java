package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.enums.AppearanceRequestStatus;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
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
public class AppearanceRequestServiceTest {
    @Mock
    AppearanceRequestRepository appearanceRequestRepository;

    @InjectMocks
    AppearanceRequestService appearanceRequestService;

    List<AppearanceRequest> appearanceRequests;

    @BeforeEach
    void setUp() {
        AppearanceRequest a1 = new AppearanceRequest();
        a1.setId(1);
        a1.setFirstName("tom");
        a1.setLastName("lee");
        a1.setPhone("(123) 456-7901");
        a1.setEventAddress("2901 Stadium Dr, Fort Worth, TX 76109");
        a1.setEmail("tomlee@tcu.edu");
        a1.setEventTitle("TCU Party");
        a1.setTypeOfEvent("Party");
        a1.setDetailedEventDescription("Fun for everyone!");
        a1.setOtherOrganizationsInvolved("None");
        a1.setNameOfOrg("TCU PARTY NAME");
        a1.setIsOnCampus("Yes");
        a1.setSpecialInstructions("N/A");
        a1.setExpensesOrBenefits("No answer");
        a1.setOtherOrganizationsInvolved("No");
        //a1.setDate(2024-04-25 14:30:00);

        AppearanceRequest a2 = new AppearanceRequest();
        a2.setId(2);
        a2.setFirstName("Alice");
        a2.setLastName("Johnson");
        a2.setPhone("(987) 654-3210");
        a2.setEventAddress("123 Main Street, Anytown, USA");
        a2.setEmail("alice.johnson@example.com");
        a2.setEventTitle("Birthday Bash");
        a2.setTypeOfEvent("Birthday Party");
        a2.setDetailedEventDescription("Celebrate Alice's birthday with friends and family!");
        a2.setOtherOrganizationsInvolved("Local charity");
        a2.setNameOfOrg("Happy Hearts Charity");
        a2.setIsOnCampus("No");
        a2.setSpecialInstructions("Please bring a gift for donation.");
        a2.setExpensesOrBenefits("Free snacks and drinks provided.");
        a2.setOtherOrganizationsInvolved("Yes, XYZ Club");

        AppearanceRequest a3 = new AppearanceRequest();
        a3.setId(3);
        a3.setFirstName("Emily");
        a3.setLastName("Smith");
        a3.setPhone("(555) 123-4567");
        a3.setEventAddress("456 Oak Street, Springfield, USA");
        a3.setEmail("emily.smith@example.com");
        a3.setEventTitle("Family Picnic");
        a3.setTypeOfEvent("Picnic");
        a3.setDetailedEventDescription("Enjoy a day of fun and food with the family!");
        a3.setOtherOrganizationsInvolved("Local park association");
        a3.setNameOfOrg("Springfield Park Association");
        a3.setIsOnCampus("No");
        a3.setSpecialInstructions("Bring your own picnic blanket and games.");
        a3.setExpensesOrBenefits("Prizes for game winners.");
        a3.setOtherOrganizationsInvolved("No additional organizations involved.");

        AppearanceRequest a4 = new AppearanceRequest();
        a4.setId(4);
        a4.setFirstName("Michael");
        a4.setLastName("Johnson");
        a4.setPhone("(555) 555-5555");
        a4.setEventAddress("789 Maple Avenue, Cityville, USA");
        a4.setEmail("michael.johnson@example.com");
        a4.setEventTitle("Community BBQ");
        a4.setTypeOfEvent("Community Event");
        a4.setDetailedEventDescription("Join us for a community BBQ with music and games!");
        a4.setOtherOrganizationsInvolved("Local charity coalition");
        a4.setNameOfOrg("Cityville Community Foundation");
        a4.setIsOnCampus("No");
        a4.setSpecialInstructions("Please RSVP for catering purposes.");
        a4.setExpensesOrBenefits("Free BBQ meal for attendees.");
        a4.setOtherOrganizationsInvolved("Cityville Rotary Club");

        this.appearanceRequests = new ArrayList<>();
        this.appearanceRequests.add(a1);
        this.appearanceRequests.add(a2);
        this.appearanceRequests.add(a3);
        this.appearanceRequests.add(a4);

    }

    @AfterEach
    void tearDown() {

    }

    @Test

    void testFindByIdSuccess() {
        //Given
        AppearanceRequest expectedRequest = appearanceRequests.get(0);
        given(appearanceRequestRepository.findById(expectedRequest.getId())).willReturn(Optional.of(expectedRequest));

        //When

        AppearanceRequest actualRequest = appearanceRequestService.findById(expectedRequest.getId());

        //Then

        assertThat(actualRequest).isEqualTo(expectedRequest);
        verify(appearanceRequestRepository, times(1)).findById(expectedRequest.getId());

    }

    @Test
    void testFindByIdNotFound() {
        //Given
        Integer nonExistentId = 99;
        given(appearanceRequestRepository.findById(nonExistentId)).willReturn(Optional.empty());

        //When

        Throwable thrown = catchThrowable(() -> {
            appearanceRequestService.findById(nonExistentId);
        });

        //Then
        assertThat(thrown).isInstanceOf(ObjectNotFoundException.class);
        verify(appearanceRequestRepository, times(1)).findById(nonExistentId);
    }

    @Test
    void testPostSuccess() {
        // Given
        AppearanceRequest newRequest = new AppearanceRequest();
        newRequest.setFirstName("Tom");
        newRequest.setLastName("Lee");
        newRequest.setEmail("test@gmail.com");
        newRequest.setPhone("(123) 456-7901");
        newRequest.setTypeOfEvent("dumb event");
        newRequest.setEventTitle("test title");
        newRequest.setEventAddress("2901 Stadium Dr, Fort Worth, TX 76109");
        newRequest.setIsOnCampus("yes");
        newRequest.setSpecialInstructions("need light support");
        newRequest.setExpensesOrBenefits("none");
        newRequest.setOtherOrganizationsInvolved("none");
        newRequest.setDetailedEventDescription("blah blah");

        given(appearanceRequestRepository.save(newRequest)).willReturn(newRequest);

        // When
        AppearanceRequest savedRequest = appearanceRequestService.save(newRequest);

        // Then
        assertThat(savedRequest.getFirstName()).isEqualTo("Tom");
        assertThat(savedRequest.getLastName()).isEqualTo("Lee");
        assertThat(savedRequest.getEmail()).isEqualTo("test@gmail.com");
        assertThat(savedRequest.getPhone()).isEqualTo("(123) 456-7901");
        assertThat(savedRequest.getTypeOfEvent()).isEqualTo("dumb event");
        assertThat(savedRequest.getEventTitle()).isEqualTo("test title");
        assertThat(savedRequest.getEventAddress()).isEqualTo("2901 Stadium Dr, Fort Worth, TX 76109");
        assertThat(savedRequest.getIsOnCampus()).isEqualTo("yes");
        assertThat(savedRequest.getSpecialInstructions()).isEqualTo("need light support");
        assertThat(savedRequest.getExpensesOrBenefits()).isEqualTo("none");
        assertThat(savedRequest.getOtherOrganizationsInvolved()).isEqualTo("none");
        assertThat(savedRequest.getDetailedEventDescription()).isEqualTo("blah blah");

        verify(appearanceRequestRepository, times(1)).save(newRequest);
    }

    @Test
    void testFindCompletedBySuperFrogStudentIdAndDateRange() {
        // Given
        SuperFrogStudent superFrogStudent = new SuperFrogStudent(); // Assume necessary attributes are set
        LocalDateTime startDate = LocalDateTime.of(2023, 4, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 4, 15, 0, 0);
        List<AppearanceRequest> expectedRequests = List.of(new AppearanceRequest(), new AppearanceRequest()); // Assume these are correctly initialized

        given(appearanceRequestRepository.findByAssignedSuperFrogStudentAndAppearanceRequestStatusInAndDateBetween(
                superFrogStudent,
                List.of(AppearanceRequestStatus.COMPLETED),
                startDate,
                endDate
        )).willReturn(expectedRequests);

        // When
        List<AppearanceRequest> actualRequests = appearanceRequestService.findCompletedBySuperFrogStudentIdAndDateRange(superFrogStudent, startDate, endDate);

        // Then
        assertThat(actualRequests).isEqualTo(expectedRequests);
        verify(appearanceRequestRepository, times(1)).findByAssignedSuperFrogStudentAndAppearanceRequestStatusInAndDateBetween(
                superFrogStudent,
                List.of(AppearanceRequestStatus.COMPLETED),
                startDate,
                endDate
        );
    }

    @Test
    void testUpdateStatusToSubmittedToPayroll() {
        // Given
        AppearanceRequest request1 = new AppearanceRequest();
        request1.setAppearanceRequestStatus(AppearanceRequestStatus.COMPLETED);

        AppearanceRequest request2 = new AppearanceRequest();
        request2.setAppearanceRequestStatus(AppearanceRequestStatus.COMPLETED);

        List<AppearanceRequest> requests = List.of(request1, request2);

        // When
        appearanceRequestService.updateStatusToSubmittedToPayroll(requests);

        // Then
        assertThat(request1.getAppearanceRequestStatus()).isEqualTo(AppearanceRequestStatus.SUBMITTED_TO_PAYROLL);
        assertThat(request2.getAppearanceRequestStatus()).isEqualTo(AppearanceRequestStatus.SUBMITTED_TO_PAYROLL);
    }

    @Test

    void testUpdateSuccess() {

        //Given

        AppearanceRequest existingAppearance = appearanceRequests.get(0);
        AppearanceRequest updatedInfo = new AppearanceRequest();
        updatedInfo.setFirstName("UpdatedFirstName");
        updatedInfo.setLastName("UpdatedLastName");

        given(appearanceRequestRepository.findById(1)).willReturn(Optional.of(existingAppearance));
        given(appearanceRequestRepository.save(existingAppearance)).willReturn(existingAppearance);

        //When

        AppearanceRequest updatedAppearance = appearanceRequestService.update(1, updatedInfo);

        //Then

        assertThat(updatedAppearance.getFirstName()).isEqualTo("UpdatedFirstName");
        assertThat(updatedAppearance.getLastName()).isEqualTo("UpdatedLastName");
        verify(appearanceRequestRepository, times(1)).save(existingAppearance);
        verify(appearanceRequestRepository, times(1)).save(existingAppearance);

    }

    @Test
    void testUpdateSuccess2() {
        //given

        AppearanceRequest existingAppearance = appearanceRequests.get(2);
        AppearanceRequest updatedInfo = new AppearanceRequest();
        updatedInfo.setFirstName("UpdatedFirstName");
        updatedInfo.setLastName("UpdatedLastName");
        updatedInfo.setEmail("updatedEmail@tcu.edu");
        updatedInfo.setPhone("999-999-9999");
        updatedInfo.setEventAddress("999 Updated Address Dr");
        updatedInfo.setExpensesOrBenefits("Yes");
        updatedInfo.setSpecialInstructions("Need Mics");
        updatedInfo.setNameOfOrg("Blueberries");
        updatedInfo.setIsOnCampus("Yes");
        updatedInfo.setOtherOrganizationsInvolved("No");
        updatedInfo.setTypeOfEvent("Cool");
        updatedInfo.setEventTitle("Pool Party");
        updatedInfo.setDetailedEventDescription("Haha");

        given(appearanceRequestRepository.findById(2)).willReturn(Optional.of(existingAppearance));
        given(appearanceRequestRepository.save(existingAppearance)).willReturn(existingAppearance);

        //When

        AppearanceRequest updatedAppearance = appearanceRequestService.update(2, updatedInfo);

        //Then
        assertThat(updatedAppearance.getFirstName()).isEqualTo("UpdatedFirstName");
        assertThat(updatedAppearance.getLastName()).isEqualTo("UpdatedLastName");
        assertThat(updatedAppearance.getEmail()).isEqualTo("updatedEmail@tcu.edu");
        assertThat(updatedAppearance.getPhone()).isEqualTo("999-999-9999");
        assertThat(updatedAppearance.getEventAddress()).isEqualTo("999 Updated Address Dr");
        assertThat(updatedAppearance.getDetailedEventDescription()).isEqualTo("Haha");
        assertThat(updatedAppearance.getOtherOrganizationsInvolved()).isEqualTo("No");
        assertThat(updatedAppearance.getExpensesOrBenefits()).isEqualTo("Yes");
        assertThat(updatedAppearance.getSpecialInstructions()).isEqualTo("Need Mics");
        assertThat(updatedAppearance.getIsOnCampus()).isEqualTo("Yes");
        assertThat(updatedAppearance.getEventTitle()).isEqualTo("Pool Party");
        assertThat(updatedAppearance.getNameOfOrg()).isEqualTo("Blueberries");
        assertThat(updatedAppearance.getTypeOfEvent()).isEqualTo("Cool");

        verify(appearanceRequestRepository, times(1)).findById(2);
        verify(appearanceRequestRepository, times(1)).save(any(AppearanceRequest.class));
    }

    @Test
    void testUpdateNotFound() {
        //Given
        AppearanceRequest updatedInfo = new AppearanceRequest();
        updatedInfo.setFirstName("Nonexistent");
        given(appearanceRequestRepository.findById(anyInt())).willReturn(Optional.empty());

        // When & Then
        assertThrows(ObjectNotFoundException.class, () -> appearanceRequestService.update(99, updatedInfo));
        verify(appearanceRequestRepository, times(1)).findById(99);

    }

    @Test
    void testUpdateInvalidFormat() {
        //Given
        Integer requestId = 1;
        AppearanceRequest existingAppearance = appearanceRequests.get(0);
        AppearanceRequest invalidUpdateInfo = new AppearanceRequest();
        invalidUpdateInfo.setEmail("invalidEmail");
        invalidUpdateInfo.setPhone("1234567890");
        invalidUpdateInfo.setEventTitle("Invalid Event Address Format");

        given(appearanceRequestRepository.findById(requestId)).willReturn(Optional.of(existingAppearance));

        willThrow(new IllegalArgumentException("Invalid data format")).given(appearanceRequestRepository).save(any(AppearanceRequest.class));

        assertThrows(IllegalArgumentException.class, () -> appearanceRequestService.update(requestId, invalidUpdateInfo));
        verify(appearanceRequestRepository, times(1)).findById(requestId);
        verify(appearanceRequestRepository, times(1)).save(any(AppearanceRequest.class));
    }
}
