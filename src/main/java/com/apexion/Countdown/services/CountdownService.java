package com.apexion.Countdown.services;

import com.apexion.Countdown.exceptions.ExpiredDateException;
import com.apexion.Countdown.exceptions.InvalidCountdownEvent;
import com.apexion.Countdown.exceptions.InvalidUserExceptions;
import com.apexion.Countdown.models.CountdownEvent;

import java.util.Date;
import java.util.List;

public interface CountdownService {
    List<CountdownEvent> fetchAllCountdownEvent();

    List<CountdownEvent> fetchUserCountdownEvents(long userId) throws InvalidUserExceptions;

    CountdownEvent addNewCountdownEvent(long userId, String eventName, Date eventDate) throws InvalidUserExceptions, ExpiredDateException;

    String showCountdown(long eventId) throws ExpiredDateException, InvalidCountdownEvent;
}
