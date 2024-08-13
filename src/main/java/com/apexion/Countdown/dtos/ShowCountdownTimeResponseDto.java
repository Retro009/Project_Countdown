package com.apexion.Countdown.dtos;

import lombok.Data;

@Data
public class ShowCountdownTimeResponseDto {
    String countdownTime;
    ResponseStatus responseStatus;
}
