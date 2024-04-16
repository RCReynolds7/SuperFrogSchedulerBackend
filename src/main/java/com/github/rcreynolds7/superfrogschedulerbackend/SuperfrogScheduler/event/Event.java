package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "event title is required.")
    private String title;

    // Example date format: 2021-10-10 10:10:10
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotEmpty(message = "start date is required.")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotEmpty(message = "end date is required.")
    private LocalDateTime endDate;

    @NotEmpty(message = "isRecurring is required.")
    private boolean isRecurring;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recurrenceStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recurrenceEndDate;

    // Constructor

    public Event() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public LocalDateTime getRecurrenceStartDate() {
        return recurrenceStartDate;
    }

    public void setRecurrenceStartDate(LocalDateTime recurrenceStartDate) {
        this.recurrenceStartDate = recurrenceStartDate;
    }

    public LocalDateTime getRecurrenceEndDate() {
        return recurrenceEndDate;
    }

    public void setRecurrenceEndDate(LocalDateTime recurrenceEndDate) {
        this.recurrenceEndDate = recurrenceEndDate;
    }
}
