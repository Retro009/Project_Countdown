package com.apexion.Countdown.exceptions;

public class InvalidEmailException extends Exception {
    public InvalidEmailException(String invalid_email_id) {
        super(invalid_email_id);
    }
}
