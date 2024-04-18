package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest;

import java.time.LocalDateTime;
import java.util.List;

public class AppearanceRequestDetails {

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String phone;

    private final String typeOfEvent;

    private final String eventTitle;

    private String nameOfOrg;

    private final String eventAddress;

    private final String isOnCampus;

    private final String specialIntructions;

    private final String expensesOrBenefits;

    private final String otherOrganizationsInvolved;

    private final String detailedEventDescription;

    private final LocalDateTime date;

    private final List<AppearanceRequest> signedUpAppearances;
    private final List<AppearanceRequest> completedAppearances;


    public AppearanceRequestDetails(String firstName, String lastName, String email, String phone, String typeOfEvent, String eventTitle, String nameOfOrg, String eventAddress, String isOnCampus, String specialIntructions, String expensesOrBenefits, String otherOrganizationsInvolved, String detailedEventDescription, LocalDateTime date, List<AppearanceRequest> signedUpAppearances, List<AppearanceRequest> completedAppearances) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.typeOfEvent = typeOfEvent;
        this.eventTitle = eventTitle;
        this.nameOfOrg = nameOfOrg;
        this.eventAddress = eventAddress;
        this.isOnCampus = isOnCampus;
        this.specialIntructions = specialIntructions;
        this.expensesOrBenefits = expensesOrBenefits;
        this.otherOrganizationsInvolved = otherOrganizationsInvolved;
        this.detailedEventDescription = detailedEventDescription;
        this.date = date;
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

    public String getTypeOfEvent(){return typeOfEvent;}

    public LocalDateTime getDate(){return date;}

    public String getEventTitle(){return eventTitle;}

    public String getNameOfOrg(){return nameOfOrg;}

    public String getEventAddress(){return eventAddress;}

    public String getIsOnCampus() {return isOnCampus;}

    public String getSpecialIntructions() {return specialIntructions;}

    public String getExpensesOrBenefits(){return expensesOrBenefits;}

    public String getOtherOrganizationsInvolved(){return otherOrganizationsInvolved;}

    public String getDetailedEventDescription(){return detailedEventDescription;}

    public List<AppearanceRequest> getSignedUpAppearances() {
        return signedUpAppearances;
    }

    public List<AppearanceRequest> getCompletedAppearances() {
        return completedAppearances;
    }


}
