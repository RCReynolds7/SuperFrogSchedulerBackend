package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.appearanceRequest;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.enums.AppearanceRequestStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;


@Entity
public class AppearanceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "firstname is required.")
    private String firstName;

    @NotEmpty(message = "lastname is required.")
    private String lastName;

    @NotEmpty(message = "email is required.")
    @Email(message = "email must be a valid email address.")
    private String email;

    @NotEmpty(message = "phone is required.")
    @Pattern(regexp = "^\\(\\d{3}\\)\\s\\d{3}-\\d{4}$", message = "phone number must be in the format (999) 999-9999")
    private String phone;

    @NotEmpty(message = "type of event is required.")
    private String typeOfEvent;

    @NotEmpty(message = "title of event is required.")
    private String eventTitle;

    @NotEmpty(message = "event address is required.")
    @Pattern(regexp = "^\\d+\\s+([a-zA-Z0-9\\s]+)\\s*,\\s*([a-zA-Z]+)\\s*,\\s*([a-zA-Z]+)\\s*\\d{5}(-\\d{4})?$", message = "address must be in the US format (street, city, state, postal code)")
    private String eventAddress;

    @NotEmpty(message = "whether on TCU campus or not is required.")
    private String isOnCampus;

    @NotEmpty(message = "special instructions are required.")
    private String specialInstructions;

    @NotEmpty(message = "expenses or benefits are required.")
    private String expensesOrBenefits;

    @NotEmpty(message = "other organizations involved are required.")
    private String otherOrganizationsInvolved;

    @NotEmpty(message = "detailed event descripiton required.")
    private String detailedEventDescription;

    @Enumerated(EnumType.STRING) // This stores the Enum as a String in the database
    private AppearanceRequestStatus appearanceRequestStatus = AppearanceRequestStatus.PENDING;

    @ManyToOne
    private SuperFrogStudent assignedSuperFrogStudent;

    // Construct

    public AppearanceRequest() {
    }

    // Getter & Setter


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTypeOfEvent() {
        return typeOfEvent;
    }

    public void setTypeOfEvent(String typeOfEvent) {
        this.typeOfEvent = typeOfEvent;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getIsOnCampus() {
        return isOnCampus;
    }

    public void setIsOnCampus(String isOnCampus) {
        this.isOnCampus = isOnCampus;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getExpensesOrBenefits() {
        return expensesOrBenefits;
    }

    public void setExpensesOrBenefits(String expensesOrBenefits) {
        this.expensesOrBenefits = expensesOrBenefits;
    }

    public String getOtherOrganizationsInvolved() {
        return otherOrganizationsInvolved;
    }

    public void setOtherOrganizationsInvolved(String otherOrganizationsInvolved) {
        this.otherOrganizationsInvolved = otherOrganizationsInvolved;
    }

    public String getDetailedEventDescription() {
        return detailedEventDescription;
    }

    public void setDetailedEventDescription(String detailedEventDescription) {
        this.detailedEventDescription = detailedEventDescription;
    }

    public AppearanceRequestStatus getAppearanceRequestStatus() {
        return appearanceRequestStatus;
    }

    public void setAppearanceRequestStatus(AppearanceRequestStatus appearanceRequestStatus) {
        this.appearanceRequestStatus = appearanceRequestStatus;
    }

    public SuperFrogStudent getAssignedSuperFrogStudent() {
        return assignedSuperFrogStudent;
    }

    public void setAssignedSuperFrogStudent(SuperFrogStudent assignedSuperFrogStudent) {
        this.assignedSuperFrogStudent = assignedSuperFrogStudent;
    }
}




