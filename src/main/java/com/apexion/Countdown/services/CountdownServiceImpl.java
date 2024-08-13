package com.apexion.Countdown.services;

import com.apexion.Countdown.exceptions.ExpiredDateException;
import com.apexion.Countdown.exceptions.InvalidUserExceptions;
import com.apexion.Countdown.models.CountdownEvent;
import com.apexion.Countdown.models.User;
import com.apexion.Countdown.repositories.CountdownEventRepository;
import com.apexion.Countdown.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CountdownServiceImpl implements CountdownService{
    @Autowired
    CountdownEventRepository countDownRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<CountdownEvent> fetchAllCountdownEvent() {
        return countDownRepository.findAll();
    }

    @Override
    public List<CountdownEvent> fetchUserCountdownEvents(long userId) throws InvalidUserExceptions {
        User user = userRepository.findById(userId).orElseThrow(() -> new InvalidUserExceptions("Invalid user Id"));
        return countDownRepository.findByUser(user);
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

        return countDownRepository.save(event);
    }
}
