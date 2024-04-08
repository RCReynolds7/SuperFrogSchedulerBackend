package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SuperFrogStudent {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Boolean isActive;

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
}
