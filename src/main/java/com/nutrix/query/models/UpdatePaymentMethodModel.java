package com.nutrix.query.models;
import lombok.Value;

@Value
public class UpdatePaymentMethodModel {
    private String id;
    private String cardType;
    private Integer cardNumber;
    private Integer expirationDateMonth;
    private Integer expirationDateYear;
    private Integer securityCode;
    private String ownerFirstname;
    private String ownerLastname;
    private String city;
    private String billingAddress;
    private String billingAddressLine2;
    private String postalCode;
    private String country;
    private Integer phoneNumber;
}