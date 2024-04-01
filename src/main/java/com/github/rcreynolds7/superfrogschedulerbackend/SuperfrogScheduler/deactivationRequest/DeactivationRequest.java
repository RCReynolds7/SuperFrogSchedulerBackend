package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.deactivationRequest;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.spiritDirector.SpiritDirector;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent.SuperFrogStudent;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class DeactivationRequest {
    private Integer id;
    @ManyToOne
    private SpiritDirector spiritDirector;
    @ManyToOne
    private SuperFrogStudent superFrogStudent;
    private String reason;
    private LocalDateTime confirmationDate;
    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SpiritDirector getSpiritDirector() {
        return spiritDirector;
    }

    public void setSpiritDirector(SpiritDirector spiritDirector) {
        this.spiritDirector = spiritDirector;
    }

    public SuperFrogStudent getSuperFrogStudent() {
        return superFrogStudent;
    }

    public void setSuperFrogStudent(SuperFrogStudent superFrogStudent) {
        this.superFrogStudent = superFrogStudent;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(LocalDateTime confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
