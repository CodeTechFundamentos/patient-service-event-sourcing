package com.nutrix.command.domain;

import com.nutrix.command.application.Notification;
import command.CreatePaymentMethodC;
import events.PaymentMethodCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class PaymentMethodA {

    @AggregateIdentifier
    private String id;
    private String patientId;
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

    public PaymentMethodA(){
    }

    @CommandHandler
    public PaymentMethodA(CreatePaymentMethodC createPaymentMethodC){
        Notification notification = validatePaymentMethod(createPaymentMethodC);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        PaymentMethodCreatedEvent event = new PaymentMethodCreatedEvent(
                createPaymentMethodC.getId(),
                createPaymentMethodC.getPatientId(),
                createPaymentMethodC.getCardType(),
                createPaymentMethodC.getCardNumber(),
                createPaymentMethodC.getExpirationDateMonth(),
                createPaymentMethodC.getExpirationDateYear(),
                createPaymentMethodC.getSecurityCode(),
                createPaymentMethodC.getOwnerFirstname(),
                createPaymentMethodC.getOwnerLastname(),
                createPaymentMethodC.getCity(),
                createPaymentMethodC.getBillingAddress(),
                createPaymentMethodC.getBillingAddressLine2(),
                createPaymentMethodC.getPostalCode(),
                createPaymentMethodC.getCountry(),
                createPaymentMethodC.getPhoneNumber()
        );
        apply(event);
    }

    private Notification validatePaymentMethod(CreatePaymentMethodC createPaymentMethodC) {
        Notification notification = new Notification();
        validatePaymentMethodId(createPaymentMethodC.getId(), notification);
        return notification;
    }

    private void validatePaymentMethodId(String paymentMethodId, Notification notification) {
        if (paymentMethodId == null) {
            notification.addError("Payment method id is missing");
        }
    }

    //Event Sourcing Handlers

    @EventSourcingHandler
    public void on(PaymentMethodCreatedEvent paymentMethodCreatedEvent){
        this.id = paymentMethodCreatedEvent.getId();
        this.patientId = paymentMethodCreatedEvent.getPatientId();
        this.cardType = paymentMethodCreatedEvent.getCardType();
        this.cardNumber = paymentMethodCreatedEvent.getCardNumber();
        this.expirationDateMonth = paymentMethodCreatedEvent.getExpirationDateMonth();
        this.expirationDateYear = paymentMethodCreatedEvent.getExpirationDateYear();
        this.securityCode = paymentMethodCreatedEvent.getSecurityCode();
        this.ownerFirstname = paymentMethodCreatedEvent.getOwnerFirstname();
        this.ownerLastname = paymentMethodCreatedEvent.getOwnerLastname();
        this.city = paymentMethodCreatedEvent.getCity();
        this.billingAddress = paymentMethodCreatedEvent.getBillingAddress();
        this.billingAddressLine2 = paymentMethodCreatedEvent.getBillingAddressLine2();
        this.postalCode = paymentMethodCreatedEvent.getPostalCode();
        this.country = paymentMethodCreatedEvent.getCountry();
        this.phoneNumber = paymentMethodCreatedEvent.getPhoneNumber();
    }
}
