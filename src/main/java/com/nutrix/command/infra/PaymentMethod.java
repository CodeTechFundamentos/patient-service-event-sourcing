package com.nutrix.command.infra;

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
    private String id;
    @Column(name = "patientId")
    private String patientId;
    @Column(name="card_type")
    private String cardType;
    @Column(name="card_number")
    private Integer cardNumber;
    @Column(name="exporation_date_month")
    private Integer expirationDateMonth;
    @Column(name="exporation_date_year")
    private Integer expirationDateYear;
    @Column(name="security_code")
    private Integer securityCode;
    @Column(name="owner_firstname")
    private String ownerFirstname;
    @Column(name="owner_lastname")
    private String ownerLastname;
    @Column(name="city")
    private String city;
    @Column(name="billing_address")
    private String billingAddress;
    @Column(name="billing_address_line2")
    private String billingAddressLine2;
    @Column(name="postal_code")
    private String postalCode;
    @Column(name="country")
    private String country;
    @Column(name="phone_number")
    private Integer phoneNumber;
}
