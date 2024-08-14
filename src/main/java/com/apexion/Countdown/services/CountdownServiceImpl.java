package com.apexion.Countdown.services;

import com.apexion.Countdown.dtos.CountdownTime;
import com.apexion.Countdown.exceptions.ExpiredDateException;
import com.apexion.Countdown.exceptions.InvalidCountdownEvent;
import com.apexion.Countdown.exceptions.InvalidUserExceptions;
import com.apexion.Countdown.models.CountdownEvent;
import com.apexion.Countdown.models.User;
import com.apexion.Countdown.repositories.CountdownEventRepository;
import com.apexion.Countdown.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountdownServiceImpl implements CountdownService{
    @Autowired
    CountdownEventRepository countdownRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<CountdownEvent> fetchAllCountdownEvent() {
        return countdownRepository.findAll();
    }

    @Override
    public List<CountdownEvent> fetchUserCountdownEvents(long userId) throws InvalidUserExceptions {
        User user = userRepository.findById(userId).orElseThrow(() -> new InvalidUserExceptions("Invalid user Id"));
        return countdownRepository.findByUser(user);
    }

    @Override
    public CountdownEvent addNewCountdownEvent(long userId, String eventName, Date eventDate) throws InvalidUserExceptions, ExpiredDateException {
        User user = userRepository.findById(userId).orElseThrow(()-> new InvalidUserExceptions("Invalid User Id"));
        if(eventDate.before(new Date()))
            throw new ExpiredDateException("This Date is Already Expired");

        CountdownEvent event = new CountdownEvent();
        event.setEventDate(eventDate);
        event.setEventName(eventName);
        event.setUser(user);
        Date createts = new Date();
        event.setCreatets(createts);

        return countdownRepository.save(event);
    }

    @Override
    public String showCountdown(long eventId) throws ExpiredDateException, InvalidCountdownEvent {
        CountdownEvent event = countdownRepository.findById(eventId).orElseThrow(()-> new InvalidCountdownEvent("Invalid event Id"));
        Date eventDate = event.getEventDate();
        Date today = new Date();
        if(eventDate.before(today))
            throw new ExpiredDateException("This Event is expired");
        Calendar now = Calendar.getInstance();
        Calendar future = Calendar.getInstance();
        future.setTime(eventDate);

        int years = future.get(Calendar.YEAR) - now.get(Calendar.YEAR);
        int months = future.get(Calendar.MONTH) - now.get(Calendar.MONTH);
        int days = future.get(Calendar.DAY_OF_MONTH) - now.get(Calendar.DAY_OF_MONTH);
        int hours = future.get(Calendar.HOUR_OF_DAY) - now.get(Calendar.HOUR_OF_DAY);
        int minutes = future.get(Calendar.MINUTE) - now.get(Calendar.MINUTE);

        if (minutes < 0) {
            minutes += 60;
            hours--;
        }
        if (hours < 0) {
            hours += 24;
            days--;
        }
        if (days < 0) {
            months--;
            days += now.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if (months < 0) {
            months += 12;
            years--;
        }
        return years+" YEARS, "+months+" MONTHS, "+days+" DAYS, "+hours+" HOURS, "+minutes+" MINUTES ";
    }


}
