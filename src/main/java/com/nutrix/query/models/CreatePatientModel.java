package com.nutrix.query.models;
import lombok.Value;
import java.util.Date;

@Value
public class CreatePatientModel {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date createdAt;
}
