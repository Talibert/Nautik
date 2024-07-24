package com.nautik.exceptions;

public class EnrollmentNotFoundException extends RuntimeException{
    public EnrollmentNotFoundException(String message) {
        super(message);
    }
}
