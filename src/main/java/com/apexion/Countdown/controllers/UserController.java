package com.apexion.Countdown.controllers;

import com.apexion.Countdown.dtos.AddUserRequestDto;
import com.apexion.Countdown.dtos.AddUserResponseDto;
import com.apexion.Countdown.dtos.ResponseStatus;
import com.apexion.Countdown.exceptions.InvalidEmailException;
import com.apexion.Countdown.models.User;
import com.apexion.Countdown.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/v1")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/users")
    List<User> showAllUser(){
        return service.fetchAllUsers();
    }

    @PostMapping("/user")
    AddUserResponseDto addUser(@RequestBody AddUserRequestDto requestDto){
        AddUserResponseDto responseDto = new AddUserResponseDto();
        try{
            responseDto.setUser(service.addUser(requestDto.getFirstName(), requestDto.getLastName(), requestDto.getEmail(), requestDto.getDOB()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(InvalidEmailException e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
