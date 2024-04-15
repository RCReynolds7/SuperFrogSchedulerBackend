package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequest;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequestRepository;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer.Customer;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer.CustomerRepository;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudentRepository;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.enums.AppearanceRequestStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBDataInitializer implements CommandLineRunner {
    private final SuperFrogStudentRepository superFrogStudentRepository;
    private final CustomerRepository customerRepository;
    private final AppearanceRequestRepository appearanceRequestRepository;

    public DBDataInitializer(SuperFrogStudentRepository superFrogStudentRepository, CustomerRepository customerRepository, AppearanceRequestRepository appearanceRequestRepository) {
        this.superFrogStudentRepository = superFrogStudentRepository;
        this.customerRepository = customerRepository;
        this.appearanceRequestRepository = appearanceRequestRepository;
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

        customerRepository.save(c1);
        customerRepository.save(c2);
        customerRepository.save(c3);
        customerRepository.save(c4);

        AppearanceRequest a1 = new AppearanceRequest();
        a1.setFirstName("james");
        a1.setLastName("smith");
        a1.setEmail("james.smith@gmail.com");
        a1.setPhone("(123) 456-7890");
        a1.setTypeOfEvent("Birthday Party");
        a1.setEventAddress("123 Main St, Fort Worth, TX 76109");
        a1.setIsOnCampus("Yes");
        a1.setSpecialInstructions("Please bring a cake");
        a1.setExpensesOrBenefits("Free food");
        a1.setAssignedSuperFrogStudent(s1);

        AppearanceRequest a2 = new AppearanceRequest();
        a2.setFirstName("sarah");
        a2.setLastName("jones");
        a2.setEmail("sarah.jones@tcu.edu");
        a2.setPhone("(987) 654-3210");
        a2.setTypeOfEvent("Graduation Party");
        a2.setEventAddress("123 Elm St, Fort Worth, TX 76109");
        a2.setIsOnCampus("No");
        a2.setSpecialInstructions("Please bring a gift");
        a2.setExpensesOrBenefits("Free food");
        a2.setAssignedSuperFrogStudent(s2);

        AppearanceRequest a3 = new AppearanceRequest();
        a3.setFirstName("michael");
        a3.setLastName("jones");
        a3.setEmail("michael.jones@uta.edu");
        a3.setPhone("(555) 123-4567");
        a3.setTypeOfEvent("Graduation Party");
        a3.setEventAddress("123 Elm St, Arlington, TX 76019");
        a3.setIsOnCampus("No");
        a3.setSpecialInstructions("Please bring a gift");
        a3.setExpensesOrBenefits("Free food");
        a3.setAssignedSuperFrogStudent(s1);
        a3.setAppearanceRequestStatus(AppearanceRequestStatus.COMPLETED);

        appearanceRequestRepository.save(a1);
        appearanceRequestRepository.save(a2);
        appearanceRequestRepository.save(a3);
    }
}
