package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBDataInitializer implements CommandLineRunner {
    private final SuperFrogStudentRepository superFrogStudentRepository;

    public DBDataInitializer(SuperFrogStudentRepository superFrogStudentRepository) {
        this.superFrogStudentRepository = superFrogStudentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        SuperFrogStudent s1 = new SuperFrogStudent();
        s1.setId(1);
        s1.setFirstName("tom");
        s1.setLastName("lee");
        s1.setActive(true);
        s1.setPhone("12345678901");
        s1.setProfileInfo("Student tom lee");
        s1.setEmail("tomlee@tcu.edu");

        superFrogStudentRepository.save(s1);
    }
}
