package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest.AppearanceRequest;

import java.util.List;

public class SuperFrogStudentDetails {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final List<AppearanceRequest> signedUpAppearances;
    private final List<AppearanceRequest> completedAppearances;

    public SuperFrogStudentDetails(String firstName, String lastName, String email, String phone, List<AppearanceRequest> signedUpAppearances, List<AppearanceRequest> completedAppearances) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.signedUpAppearances = signedUpAppearances;
        this.completedAppearances = completedAppearances;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public List<AppearanceRequest> getSignedUpAppearances() {
        return signedUpAppearances;
    }

    public List<AppearanceRequest> getCompletedAppearances() {
        return completedAppearances;
    }
}
