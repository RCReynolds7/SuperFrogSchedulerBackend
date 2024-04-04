package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

public class SuperFrogStudentNotFoundException extends RuntimeException {
    public SuperFrogStudentNotFoundException(Integer id) {
        super("Could not find SuperFrog student with Id " + id);
    }
}