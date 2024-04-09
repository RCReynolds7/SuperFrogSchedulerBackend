package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.SuperFrogStudent;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;



@Entity
public class AppearanceRequest implements Serializable {


// private
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer requestId;

    private Integer customerId;

    private Integer eventId;

    private Integer directorId;

    private String status;

    private EventType eventType;

    private String EventTitle;

    private String address;

    private LocalDate eventDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String nameOfOrg;

    private String description;

    private String specialInstructions;

    private String externalOrgs;

    private String expenses;

    private String notAtTCU;

    private String requestDate;

    private String approvalDate;


    // Setters and Getters

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEventTitle() {
        return EventTitle;
    }

    public void setEventTitle(String EventTitle) {
        this.EventTitle = title;
    }

    public String getNameOfOrg() {
        return nameOfOrg;
    }

    public void setNameOfOrg(String nameOfOrg) {
        this.nameOfOrg = nameOfOrg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getExternalOrgs() {
        return outsideOrgs;
    }

    public void setExternalOrgs(String outsideOrgs) {
        this.outsideOrgs = outsideOrgs;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    @ManyToOne
    private SuperFrogStudent student;


    public SuperFrogAppearanceRequest() {
    }

    // public
    public AppearanceRequest(Integer requestId, EventType eventType, String address, LocalDate eventDate, LocalTime startTime, LocalTime endTime, String status, String firstName, String lastName, String phone, String email, String title, String nameOfOrg, String description, String specialInstructions, String externalOrgs, String expenses, SuperFrogStudent student, String notAtTCU, Integer customerId, Integer directorId, String approvalDate, String requestDate, Integer eventId) {
        this.requestId = requestId;
        this.eventType = eventType;
        this.firstName = firstName;
        this.fastName = lastName;
        this.phone = phone;
        this.email = email;
        this.title = title;
        this.nameOfOrg = nameOfOrg;
        this.description = description;
        this.specialInstructions = specialInstructions;
        this.externalOrgs = externalOrgs;
        this.expenses = expenses;
        this.eventAddress = eventAddress;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.student = student;
        this.notAtTCU = notAtTCU;
        this.customerId = customerId;
        this.directorId = directorId;
        this.approvalDate = approvalDate;
        this.requestDate = requestDate;
        this.eventId = eventId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public status getstatus() {
        return status;
    }

    public EventType getEventType() {
        return eventType;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public SuperFrogStudent getStudent() {
        return student;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void setStudent(SuperFrogStudent student) {
        this.student = student;
    }
    public String getnotAtTCU() {
        return notAtTCU;
    }

    public Integer getdirectorId() {
        return directorId;
    }

    public Integer getcustomerId() {
        return customerId;
    }

    public String approvalDate() {
        return approvalDate;
    }

    public String requestDate() {
        return requestDate;
    }

    public String requestDetails() {
        return requestDetails;
    }
}


    

