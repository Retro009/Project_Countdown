package com.apexion.Countdown.controllers;

import com.apexion.Countdown.dtos.*;
import com.apexion.Countdown.exceptions.ExpiredDateException;
import com.apexion.Countdown.exceptions.InvalidUserExceptions;
import com.apexion.Countdown.models.CountdownEvent;
import com.apexion.Countdown.services.CountdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountdownController {
    @Autowired
    CountdownService service;

    List<CountdownEvent> showAllEvents(){
        return service.fetchAllCountdownEvent();
    }

    ShowUserCountdownEventsResponseDto showUserCountdownEvents(ShowUserCountdownEventsRequestDto requestDto){
        ShowUserCountdownEventsResponseDto responseDto = new ShowUserCountdownEventsResponseDto();
        try{
            responseDto.setCountDownEvents(service.fetchUserCountdownEvents(requestDto.getUserId()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(InvalidUserExceptions e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    AddUserCountdownEventResponseDto addUserCountDownEvent(AddUserCountdownEventRequestDto requestDto) throws InvalidUserExceptions {
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
