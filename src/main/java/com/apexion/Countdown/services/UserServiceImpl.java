package com.apexion.Countdown.services;

import com.apexion.Countdown.exceptions.InvalidEmailException;
import com.apexion.Countdown.models.User;
import com.apexion.Countdown.repositories.UserRepository;
import com.apexion.Countdown.utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public User addUser(String firstName, String lastName, String email, Date DOB) throws InvalidEmailException{
        if(!EmailValidator.isValidEmail(email))
            throw new InvalidEmailException("Invalid Email id");
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDOB(DOB);
        user.setEmail(email);

        Date createts = new Date();
        user.setCreatets(createts);
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }
}
