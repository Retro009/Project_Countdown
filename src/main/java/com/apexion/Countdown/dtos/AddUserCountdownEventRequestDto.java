package com.apexion.Countdown.dtos;

import com.apexion.Countdown.models.User;
import lombok.Data;

import java.util.Date;

@Data
public class AddUserCountdownEventRequestDto {
    long userId;
    String eventName;
    Date eventDate;
}
