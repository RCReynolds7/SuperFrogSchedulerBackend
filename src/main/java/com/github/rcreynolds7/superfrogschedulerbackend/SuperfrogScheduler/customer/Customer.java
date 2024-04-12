package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
public class Customer {
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

    // Construct
    public Customer(String ana, String park, String mail, String s) {

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
}