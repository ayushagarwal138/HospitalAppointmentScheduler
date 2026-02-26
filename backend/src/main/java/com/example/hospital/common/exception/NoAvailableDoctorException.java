package com.example.hospital.common.exception;

public class NoAvailableDoctorException extends RuntimeException {

    public NoAvailableDoctorException(String message) {
        super(message);
    }
}

