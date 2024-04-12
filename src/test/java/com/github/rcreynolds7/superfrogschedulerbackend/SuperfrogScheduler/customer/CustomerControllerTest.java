package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer;


import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService CustomerService;

    @Autowired
    ObjectMapper objectMapper;

    List<Customer> customer;

    @Value("${api.endpoint.base-url}/customer")
    String baseUrl;

    @BeforeEach
    void setUp() {
        Customer c1 = new Customer("ana", "park", "anapark@gmail.com", "(563) 522-7491");
        c1.setFirstName("ana");
        c1.setLastName("park");
        c1.setEmail("anapark@gmail.com");
        c1.setPhone("(563) 522-7491");

        Customer c2 = new Customer("ana", "park", "anapark@gmail.com", "(563) 522-7491");
        c2.setFirstName("john");
        c2.setLastName("smith");
        c2.setEmail("johnsmith@gmail.com");
        c2.setPhone("(123) 456-7890");

        Customer c3 = new Customer("ana", "park", "anapark@gmail.com", "(563) 522-7491");
        c3.setFirstName("alice");
        c3.setLastName("brown");
        c3.setEmail("alicebrown@gmail.com");
        c3.setPhone("(987) 654-3210");

        Customer c4 = new Customer("ana", "park", "anapark@gmail.com", "(563) 522-7491");
        c4.setFirstName("michael");
        c4.setLastName("jones");
        c4.setEmail("michaeljones@gmail.com");
        c4.setPhone("(555) 123-4567");

        this.customer = new ArrayList<>();

        this.customer.add(c1);
        this.customer.add(c2);
        this.customer.add(c3);
        this.customer.add(c4);

    }


   @AfterEach()
   void tearDown() {
   }

   @Test
   void testFindCustomerByIdSuccess() throws Exception {
       // Given
       int customerId = 1; // Assuming customerId is 1 for this test case
       Customer mockCustomer = new Customer("ana", "park", "anapark@gmail.com", "(563) 522-7491");
       given(CustomerService.findById(customerId)).willReturn(mockCustomer);

       // When & Then
       mockMvc.perform(get("/api/customer/{customerId}", customerId)
                       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.firstName").value("ana"))
               .andExpect(jsonPath("$.lastName").value("park"))
               .andExpect(jsonPath("$.email").value("anapark@gmail.com"))
               .andExpect(jsonPath("$.phone").value("(563) 522-7491"));

   }








}
