package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.performanceReport;

import java.time.LocalDateTime;

public class PerformanceReport {
    private Integer superFrogStudentId;
    private String superFrogStudentFirstName;
    private String superFrogStudentLastName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer completedAppearances;
    private Integer cancelledAppearances;

    public PerformanceReport() {
    }

    public Integer getSuperFrogStudentId() {
        return superFrogStudentId;
    }

    public void setSuperFrogStudentId(Integer superFrogStudentId) {
        this.superFrogStudentId = superFrogStudentId;
    }

    public String getSuperFrogStudentFirstName() {
        return superFrogStudentFirstName;
    }

    public void setSuperFrogStudentFirstName(String superFrogStudentFirstName) {
        this.superFrogStudentFirstName = superFrogStudentFirstName;
    }

    public String getSuperFrogStudentLastName() {
        return superFrogStudentLastName;
    }

    public void setSuperFrogStudentLastName(String superFrogStudentLastName) {
        this.superFrogStudentLastName = superFrogStudentLastName;
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

    public Integer getCompletedAppearances() {
        return completedAppearances;
    }

    public void setCompletedAppearances(Integer completedAppearances) {
        this.completedAppearances = completedAppearances;
    }

    public Integer getCancelledAppearances() {
        return cancelledAppearances;
    }

    public void setCancelledAppearances(Integer cancelledAppearances) {
        this.cancelledAppearances = cancelledAppearances;
    }
}
