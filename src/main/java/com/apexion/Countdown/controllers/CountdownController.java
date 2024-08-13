package com.apexion.Countdown.controllers;

import com.apexion.Countdown.dtos.*;
import com.apexion.Countdown.dtos.ResponseStatus;
import com.apexion.Countdown.exceptions.ExpiredDateException;
import com.apexion.Countdown.exceptions.InvalidCountdownEvent;
import com.apexion.Countdown.exceptions.InvalidUserExceptions;
import com.apexion.Countdown.models.CountdownEvent;
import com.apexion.Countdown.services.CountdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countdownApi/v1")
public class CountdownController {
    @Autowired
    CountdownService service;

    @GetMapping("/events")
    List<CountdownEvent> showAllEvents(){
        return service.fetchAllCountdownEvent();
    }

    @GetMapping("/userEvents")
    ShowUserCountdownEventsResponseDto showUserCountdownEvents(@RequestBody  ShowUserCountdownEventsRequestDto requestDto){
        ShowUserCountdownEventsResponseDto responseDto = new ShowUserCountdownEventsResponseDto();
        try{
            responseDto.setCountDownEvents(service.fetchUserCountdownEvents(requestDto.getUserId()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(InvalidUserExceptions e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    @GetMapping("/countdownTime")
    ShowCountdownTimeResponseDto showCountdownTime(@RequestBody ShowCountdownTimeRequestDto requestDto){
        ShowCountdownTimeResponseDto responseDto = new ShowCountdownTimeResponseDto();
        try {
            responseDto.setCountdownTime(service.showCountdown(requestDto.getEventId()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InvalidCountdownEvent | ExpiredDateException e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    @PostMapping("/userEvents")
    AddUserCountdownEventResponseDto addUserCountDownEvent(@RequestBody AddUserCountdownEventRequestDto requestDto) throws InvalidUserExceptions {
        AddUserCountdownEventResponseDto responseDto = new AddUserCountdownEventResponseDto();
        try{
            responseDto.setCountdownEvent(service.addNewCountdownEvent(requestDto.getUserId(),requestDto.getEventName(),requestDto.getEventDate()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (InvalidUserExceptions | ExpiredDateException e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

}
