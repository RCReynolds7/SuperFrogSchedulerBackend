package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.PaymentPreference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
public class SuperFrogStudent {
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

    @NotEmpty(message = "address is required.")
    @Pattern(regexp = "^\\d+\\s+([a-zA-Z0-9\\s]+)\\s*,\\s*([a-zA-Z]+)\\s*,\\s*([a-zA-Z]+)\\s*\\d{5}(-\\d{4})?$", message = "address must be in the US format (street, city, state, postal code)")
    private String address;

    private Boolean isActive = true;

    private Boolean isInternational = false;

    @Enumerated(EnumType.STRING) // This stores the Enum as a String in the database
    private PaymentPreference paymentPreference = PaymentPreference.MAIL_CHECK;

    // Constructor
    public SuperFrogStudent() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getInternational() {
        return isInternational;
    }

    public void setInternational(Boolean international) {
        isInternational = international;
    }

    public PaymentPreference getPaymentPreference() {
        return paymentPreference;
    }

    public void setPaymentPreference(PaymentPreference paymentPreference) {
        this.paymentPreference = paymentPreference;
    }
}
