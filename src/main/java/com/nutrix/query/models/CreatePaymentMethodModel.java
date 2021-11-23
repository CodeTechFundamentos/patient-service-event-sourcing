package com.nutrix.query.models;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
public class CreatePaymentMethodModel {
    @ApiModelProperty(notes = "Patient Id",name="patientId",required=true,example = "88c061a3-b67c-48eb-a63d-49d5bdf9e836")
    private String patientId;
    @ApiModelProperty(notes = "Payment method card type",name="cardType",required=true,example = "Debito")
    private String cardType;
    @ApiModelProperty(notes = "Payment method card number",name="cardNumber",required=true,example = "2147483647")
    private Integer cardNumber;
    @ApiModelProperty(notes = "Payment method expiration date month",name="expirationDateMonth",required=true,example = "04")
    private Integer expirationDateMonth;
    @ApiModelProperty(notes = "Payment method expiration date year",name="expirationDateYear",required=true,example = "2025")
    private Integer expirationDateYear;
    @ApiModelProperty(notes = "Payment method security code",name="securityCode",required=true,example = "2504")
    private Integer securityCode;
    @ApiModelProperty(notes = "Payment method owner first name",name="owner_firstname",required=true,example = "Josue")
    private String ownerFirstname;
    @ApiModelProperty(notes = "Payment method owner last name",name="owner_lastname",required=true,example = "Cuentas Jave")
    private String ownerLastname;
    @ApiModelProperty(notes = "Payment method city",name="city",required=true,example = "Lima")
    private String city;
    @ApiModelProperty(notes = "Payment method billing address",name="billingAddress",required=true,example = "Jr. Ancash 16")
    private String billingAddress;
    @ApiModelProperty(notes = "Payment method billing address line 2",name="billingAddressLine2",required=true,example = "Manuel Odria 278")
    private String billingAddressLine2;
    @ApiModelProperty(notes = "Payment method postal code",name="postalCode",required=true,example = "15003")
    private String postalCode;
    @ApiModelProperty(notes = "Payment method country",name="country",required=true,example = "Perú")
    private String country;
    @ApiModelProperty(notes = "Payment method phone number",name="phoneNumber",required=true,example = "960104625")
    private Integer phoneNumber;
}