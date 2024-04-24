package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequest;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequestService;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.dto.AppearanceRequestDto;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.StatusCode;
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


import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService CustomerService;

    @MockBean
    AppearanceRequestService appearanceRequestService;

    @Autowired
    ObjectMapper objectMapper;

    List<Customer> customers;


    @Value("${api.endpoint.base-url}/customer")
    String baseUrl;

    @BeforeEach
    void setUp() {
        Customer c1 = new Customer();
        c1.setFirstName("ana");
        c1.setLastName("park");
        c1.setEmail("anapark@gmail.com");
        c1.setPhone("(563) 522-7491");

        Customer c2 = new Customer();
        c2.setFirstName("john");
        c2.setLastName("smith");
        c2.setEmail("johnsmith@gmail.com");
        c2.setPhone("(123) 456-7890");

        Customer c3 = new Customer();
        c3.setFirstName("alice");
        c3.setLastName("brown");
        c3.setEmail("alicebrown@gmail.com");
        c3.setPhone("(987) 654-3210");

        Customer c4 = new Customer();
        c4.setFirstName("michael");
        c4.setLastName("jones");
        c4.setEmail("michaeljones@gmail.com");
        c4.setPhone("(555) 123-4567");

        this.customers = new ArrayList<>();

        this.customers.add(c1);
        this.customers.add(c2);
        this.customers.add(c3);
        this.customers.add(c4);
    }


    @AfterEach()
    void tearDown() {
    }

//    @Test
//    void testPostAppearanceRequestSuccess() throws Exception {
//        // Given
//        AppearanceRequestDto appearanceRequestDto = new AppearanceRequestDto(1, "Tom", "Lee", "test@gmail.com", "(123) 456-7901", "dumb event", "test title", "2901 Stadium Dr, Fort Worth, TX 76109", "yes", "need light support", "none", "none", "blah blah", "Pending");
//        String json = this.objectMapper.writeValueAsString(appearanceRequestDto);
//
//        AppearanceRequest savedAppearanceRequest = new AppearanceRequest();
//        savedAppearanceRequest.setId(1);
//        savedAppearanceRequest.setFirstName("Tom");
//        savedAppearanceRequest.setLastName("Lee");
//        savedAppearanceRequest.setEmail("test@gmail.com");
//        savedAppearanceRequest.setPhone("(123) 456-7901");
//        savedAppearanceRequest.setTypeOfEvent("dumb event");
//        savedAppearanceRequest.setEventTitle("test title");
//        savedAppearanceRequest.setEventAddress("2901 Stadium Dr, Fort Worth, TX 76109");
//        savedAppearanceRequest.setIsOnCampus("yes");
//        savedAppearanceRequest.setSpecialInstructions("need light support");
//        savedAppearanceRequest.setExpensesOrBenefits("none");
//        savedAppearanceRequest.setOtherOrganizationsInvolved("none");
//        savedAppearanceRequest.setDetailedEventDescription("blah blah");
//
//        this.mockMvc.perform(post(this.baseUrl + "/request-superfrog-appearance").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.flag").value(true))
//                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
//                .andExpect(jsonPath("$.message").value("SuperFrog appearance request created successfully."))
//                .andExpect(jsonPath("$.data.id").isNotEmpty())
//                .andExpect(jsonPath("$.data.firstName").value(savedAppearanceRequest.getFirstName()));
//
//
//    }

//    @Test
//    void testPostAppearanceRequestErrorWithEmptyField() throws Exception {
//        // Given
//        AppearanceRequestDto appearanceRequestDto = new AppearanceRequestDto(1, "Tom", "", "test@gmail.com", "(123) 456-7901", "dumb event", "test title", "2901 Stadium Dr, Fort Worth, TX 76109", "yes", "need light support", "none", "none", "blah blah", "Pending");
//        String json = this.objectMapper.writeValueAsString(appearanceRequestDto);
//
//        AppearanceRequest savedAppearanceRequest = new AppearanceRequest();
//        savedAppearanceRequest.setId(1);
//        savedAppearanceRequest.setFirstName("Tom");
//        savedAppearanceRequest.setLastName("");
//        savedAppearanceRequest.setEmail("test@gmail.com");
//        savedAppearanceRequest.setPhone("(123) 456-7901");
//        savedAppearanceRequest.setTypeOfEvent("dumb event");
//        savedAppearanceRequest.setEventTitle("test title");
//        savedAppearanceRequest.setEventAddress("2901 Stadium Dr, Fort Worth, TX 76109");
//        savedAppearanceRequest.setIsOnCampus("yes");
//        savedAppearanceRequest.setSpecialInstructions("need light support");
//        savedAppearanceRequest.setExpensesOrBenefits("none");
//        savedAppearanceRequest.setOtherOrganizationsInvolved("none");
//        savedAppearanceRequest.setDetailedEventDescription("blah blah");
//
//        this.mockMvc.perform(post(this.baseUrl + "/request-superfrog-appearance").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.flag").value(true))
//                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
//                .andExpect(jsonPath("$.message").value("SuperFrog appearance request created successfully."))
//                .andExpect(jsonPath("$.data.id").isNotEmpty())
//                .andExpect(jsonPath("$.data.firstName").value(savedAppearanceRequest.getFirstName()));
//    }

    }



