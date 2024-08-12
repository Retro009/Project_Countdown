package com.apexion.Countdown.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;
@Entity
@Table(name = "users")
@Data
public class User extends BaseModel {
    private String firstName;
    private String lastName;
    private Date DOB;
    private String email;
}
