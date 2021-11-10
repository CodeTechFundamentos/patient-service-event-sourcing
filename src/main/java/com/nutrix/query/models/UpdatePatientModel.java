package com.nutrix.query.models;
import lombok.Value;

@Value
public class UpdatePatientModel {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
