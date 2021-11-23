package com.nutrix.query.models;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;
import java.util.Date;

@Value
public class CreatePatientModel {
    @ApiModelProperty(notes = "Patient username",name="username",required=true,example = "JosueCuentas")
    private String username;
    @ApiModelProperty(notes = "Patient password",name="password",required=true,example = "password1")
    private String password;
    @ApiModelProperty(notes = "Patient firstname",name="firstName",required=true,example = "Josue")
    private String firstName;
    @ApiModelProperty(notes = "Patient lastname",name="lastName",required=true,example = "Cuentas Jave")
    private String lastName;
    @ApiModelProperty(notes = "Patient email",name="email",required=true,example = "josue@gmail.com")
    private String email;
    @ApiModelProperty(notes = "Patient created date",name="createdAt",required=true,example = "2021-11-23T04:25:17.917+00:00")
    private Date createdAt;
}
