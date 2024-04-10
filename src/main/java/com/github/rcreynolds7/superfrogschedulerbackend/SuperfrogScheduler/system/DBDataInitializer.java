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
        s1.setFirstName("tom");
        s1.setLastName("lee");
        s1.setActive(true);
        s1.setPhone("123-456-7901");
        s1.setAddress("2901 Stadium Dr, TX");
        s1.setEmail("tomlee@tcu.edu");

        SuperFrogStudent s2 = new SuperFrogStudent();
        s2.setFirstName("andre");
        s2.setLastName("gomez");
        s2.setActive(false);
        s2.setPhone("263-456-7891");
        s2.setAddress("3000 McCart Ave, TX");
        s2.setEmail("andregomez@tcu.edu");

        SuperFrogStudent s3 = new SuperFrogStudent();
        s3.setFirstName("jonny");
        s3.setLastName("long");
        s3.setActive(true);
        s3.setPhone("243-556-7891");
        s3.setAddress("123 Park Ave, OK");
        s3.setEmail("jonny@asu.edu");

        SuperFrogStudent s4 = new SuperFrogStudent();
        s4.setFirstName("ana");
        s4.setLastName("park");
        s4.setActive(false);
        s4.setPhone("545-522-7491");
        s4.setAddress("Kensington Ave, NY");
        s4.setEmail("ana@gmail.com");

        superFrogStudentRepository.save(s1);
        superFrogStudentRepository.save(s2);
        superFrogStudentRepository.save(s3);
        superFrogStudentRepository.save(s4);

    }
}
