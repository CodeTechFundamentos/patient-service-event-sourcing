package com.nutrix.command.infra;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="payment_method")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod{
    @Id
    @ApiModelProperty(notes = "Payment method Id",name="paymentMethodId",required=true,example = "1c3433ba-2f95-459e-b29c-5ed914adcb94")
    private String id;
    @Column(name = "patientId")
    @ApiModelProperty(notes = "Patient Id",name="patientId",required=true,example = "88c061a3-b67c-48eb-a63d-49d5bdf9e836")
    private String patientId;
    @Column(name="card_type")
    @ApiModelProperty(notes = "Payment method card type",name="cardType",required=true,example = "Debito")
    private String cardType;
    @Column(name="card_number")
    @ApiModelProperty(notes = "Payment method card number",name="cardNumber",required=true,example = "2147483647")
    private Integer cardNumber;
    @Column(name="exporation_date_month")
    @ApiModelProperty(notes = "Payment method expiration date month",name="expirationDateMonth",required=true,example = "04")
    private Integer expirationDateMonth;
    @Column(name="exporation_date_year")
    @ApiModelProperty(notes = "Payment method expiration date year",name="expirationDateYear",required=true,example = "2025")
    private Integer expirationDateYear;
    @Column(name="security_code")
    @ApiModelProperty(notes = "Payment method security code",name="securityCode",required=true,example = "2504")
    private Integer securityCode;
    @Column(name="owner_firstname")
    @ApiModelProperty(notes = "Payment method owner first name",name="owner_firstname",required=true,example = "Josue")
    private String ownerFirstname;
    @Column(name="owner_lastname")
    @ApiModelProperty(notes = "Payment method owner last name",name="owner_lastname",required=true,example = "Cuentas Jave")
    private String ownerLastname;
    @Column(name="city")
    @ApiModelProperty(notes = "Payment method city",name="city",required=true,example = "Lima")
    private String city;
    @Column(name="billing_address")
    @ApiModelProperty(notes = "Payment method billing address",name="billingAddress",required=true,example = "Jr. Ancash 16")
    private String billingAddress;
    @Column(name="billing_address_line2")
    @ApiModelProperty(notes = "Payment method billing address line 2",name="billingAddressLine2",required=true,example = "Manuel Odria 278")
    private String billingAddressLine2;
    @Column(name="postal_code")
    @ApiModelProperty(notes = "Payment method postal code",name="postalCode",required=true,example = "15003")
    private String postalCode;
    @Column(name="country")
    @ApiModelProperty(notes = "Payment method country",name="country",required=true,example = "Perú")
    private String country;
    @Column(name="phone_number")
    @ApiModelProperty(notes = "Payment method phone number",name="phoneNumber",required=true,example = "960104625")
    private Integer phoneNumber;
}
