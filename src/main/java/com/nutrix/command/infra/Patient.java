package com.nutrix.command.infra;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient{
    @Id
    @ApiModelProperty(notes = "Patient Id",name="patientId",required=true,example = "88c061a3-b67c-48eb-a63d-49d5bdf9e836")
    private String id;
    @Column(name = "username")
    @ApiModelProperty(notes = "Patient username",name="username",required=true,example = "JosueCuentas")
    private String username;
    @Column(name = "password")
    @ApiModelProperty(notes = "Patient password",name="password",required=true,example = "password1")
    private String password;
    @Column(name="firstname")
    @ApiModelProperty(notes = "Patient firstname",name="firstName",required=true,example = "Josue")
    private String firstName;
    @Column(name="lastname")
    @ApiModelProperty(notes = "Patient lastname",name="lastName",required=true,example = "Cuentas Jave")
    private String lastName;
    @Column(name="email")
    @ApiModelProperty(notes = "Patient email",name="email",required=true,example = "josue@gmail.com")
    private String email;
    @Column(name="created_at")
    @ApiModelProperty(notes = "Patient created date",name="createdAt",required=true,example = "2021-11-23T04:25:17.917+00:00")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}