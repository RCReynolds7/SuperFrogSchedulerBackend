package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.exception;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String objectName, Integer id) {
        super("Could not find " + objectName + " with Id " + id + " :(");
    }
}