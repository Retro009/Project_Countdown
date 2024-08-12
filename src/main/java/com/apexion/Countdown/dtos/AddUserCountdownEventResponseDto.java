package com.apexion.Countdown.dtos;

import com.apexion.Countdown.models.CountdownEvent;
import lombok.Data;

@Data
public class AddUserCountdownEventResponseDto {
    CountdownEvent countdownEvent;
    ResponseStatus responseStatus;
}
