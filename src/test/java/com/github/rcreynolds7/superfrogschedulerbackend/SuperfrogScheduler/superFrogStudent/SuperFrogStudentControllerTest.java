package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.dto.SuperFrogStudentDto;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.StatusCode;
import org.aspectj.lang.annotation.After;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SuperFrogStudentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    SuperFrogStudentService superFrogStudentService;

    @Autowired
    ObjectMapper objectMapper;

    List<SuperFrogStudent> superFrogStudents;

    @Value("${api.endpoint.base-url}/superfrog-students")
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

}
