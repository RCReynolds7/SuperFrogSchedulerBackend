package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
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

    }

    @AfterEach
    void tearDown() {

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
}
