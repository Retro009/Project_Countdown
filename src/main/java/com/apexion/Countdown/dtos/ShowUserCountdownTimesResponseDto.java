package com.apexion.Countdown.dtos;

import java.util.List;

public record ShowUserCountdownTimesResponseDto(List<CountdownTime> countdownTimes, ResponseStatus responseStatus) {
}
