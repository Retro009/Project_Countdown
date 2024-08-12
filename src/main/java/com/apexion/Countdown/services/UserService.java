package com.apexion.Countdown.services;

import com.apexion.Countdown.exceptions.InvalidEmailException;
import com.apexion.Countdown.models.User;

import java.util.Date;
import java.util.List;

public interface UserService {
    User addUser(String firstName, String lastName, String email, Date DOB) throws InvalidEmailException;
    List<User> fetchAllUsers();
}
