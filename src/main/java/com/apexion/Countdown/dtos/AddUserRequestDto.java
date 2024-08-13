package com.apexion.Countdown.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AddUserRequestDto {
    String firstName;
    String lastName;
    String email;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX", timezone = "UTC")
    Date DOB;
}
