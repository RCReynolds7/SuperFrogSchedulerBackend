package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer.Customer;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer.CustomerRepository;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBDataInitializer implements CommandLineRunner {
    private final SuperFrogStudentRepository superFrogStudentRepository;
    private final CustomerRepository customerRepository;

    public DBDataInitializer(SuperFrogStudentRepository superFrogStudentRepository, CustomerRepository customerRepository) {
        this.superFrogStudentRepository = superFrogStudentRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        SuperFrogStudent s1 = new SuperFrogStudent();
        s1.setFirstName("tom");
        s1.setLastName("lee");
        s1.setActive(true);
        s1.setPhone("(123) 456-7901");
        s1.setAddress("2901 Stadium Dr, Fort Worth, TX 76109");
        s1.setEmail("tomlee@tcu.edu");

        SuperFrogStudent s2 = new SuperFrogStudent();
        s2.setFirstName("andre");
        s2.setLastName("gomez");
        s2.setActive(false);
        s2.setPhone("(263) 456-7891");
        s2.setAddress("3000 McCart Ave, Fort Worth, TX 76133");
        s2.setEmail("andregomez@tcu.edu");

        SuperFrogStudent s3 = new SuperFrogStudent();
        s3.setFirstName("jonny");
        s3.setLastName("long");
        s3.setActive(true);
        s3.setPhone("(243) 556-7891");
        s3.setAddress("123 Park Ave, Oklahoma City, OK 73102");
        s3.setEmail("jonny@asu.edu");

        SuperFrogStudent s4 = new SuperFrogStudent();
        s4.setFirstName("ana");
        s4.setLastName("park");
        s4.setActive(false);
        s4.setPhone("(545) 522-7491");
        s4.setAddress("123 Kensington Ave, Brooklyn, NY 11218");
        s4.setEmail("ana@gmail.com");

        superFrogStudentRepository.save(s1);
        superFrogStudentRepository.save(s2);
        superFrogStudentRepository.save(s3);
        superFrogStudentRepository.save(s4);

        Customer c1 = new Customer();
        c1.setFirstName("ana");
        c1.setLastName("park");
        c1.setEmail("anapark@gmail.com");
        c1.setPhone("(563) 522-7491");

        customerRepository.save(c1);

    }
}
