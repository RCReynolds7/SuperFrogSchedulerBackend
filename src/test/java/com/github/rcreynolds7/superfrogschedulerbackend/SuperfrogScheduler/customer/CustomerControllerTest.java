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

    List<Customer> customers;

    @Value("{${api.endpoint.base-url}/customer")
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
}
