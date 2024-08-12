package com.apexion.Countdown.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class CountdownEvent extends BaseModel {
    private String eventName;
    private Date eventDate;
    @ManyToOne
    private User user;
}
