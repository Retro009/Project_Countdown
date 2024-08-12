package com.apexion.Countdown.dtos;

import com.apexion.Countdown.models.CountdownEvent;
import lombok.Data;

import java.util.List;

@Data
public class ShowUserCountdownEventsResponseDto {
    List<CountdownEvent> countDownEvents;
    ResponseStatus responseStatus;
}
