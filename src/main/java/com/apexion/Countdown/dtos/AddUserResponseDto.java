package com.apexion.Countdown.dtos;

import com.apexion.Countdown.models.User;
import lombok.Data;

@Data
public class AddUserResponseDto {
    User user;
    ResponseStatus responseStatus;
}
