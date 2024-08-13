package com.apexion.Countdown.exceptions;

public class InvalidCountdownEvent extends Exception {
    public InvalidCountdownEvent(String invalid_event_id) {
        super(invalid_event_id);
    }
}
