package com.nutrix.command.application.handlers;

import com.nutrix.command.infra.IPatientRepository;
import com.nutrix.command.infra.IPaymentMethodRepository;
import com.nutrix.command.infra.Patient;
import com.nutrix.command.infra.PaymentMethod;
import events.PatientCreatedEvent;
import events.PaymentMethodCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("payment_method")
public class PaymentMethodEventHandler {
    private final IPaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodEventHandler(IPaymentMethodRepository paymentMethodRepository){
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @EventHandler
    public void on(PaymentMethodCreatedEvent event){
        paymentMethodRepository.save(new PaymentMethod(
                        event.getId(),
                        event.getPatientId(),
                        event.getCardType(),
                        event.getCardNumber(),
                        event.getExpirationDateMonth(),
                        event.getExpirationDateYear(),
                        event.getSecurityCode(),
                        event.getOwnerFirstname(),
                        event.getOwnerLastname(),
                        event.getCity(),
                        event.getBillingAddress(),
                        event.getBillingAddressLine2(),
                        event.getPostalCode(),
                        event.getCountry(),
                        event.getPhoneNumber()
                )
        );
    }
}
