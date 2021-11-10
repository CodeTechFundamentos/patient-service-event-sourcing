package com.nutrix.query.application.handlers;
import com.nutrix.command.infra.IPaymentMethodRepository;
import com.nutrix.command.infra.PaymentMethod;
import com.nutrix.query.models.CreatePaymentMethodModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import queries.GetPaymentMethodsQuery;
import result.PaymentMethodResult;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMethodQueryHandler {
    private final IPaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodQueryHandler(IPaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @QueryHandler
    public List<PaymentMethodResult> handle(GetPaymentMethodsQuery query) {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();

        List<PaymentMethodResult> paymentMethodModels =
                paymentMethods.stream()
                        .map(paymentMethod -> new PaymentMethodResult(
                                paymentMethod.getId(),
                                paymentMethod.getPatientId(),
                                paymentMethod.getCardType(),
                                paymentMethod.getCardNumber(),
                                paymentMethod.getExpirationDateMonth(),
                                paymentMethod.getExpirationDateYear(),
                                paymentMethod.getSecurityCode(),
                                paymentMethod.getOwnerFirstname(),
                                paymentMethod.getOwnerLastname(),
                                paymentMethod.getCity(),
                                paymentMethod.getBillingAddress(),
                                paymentMethod.getBillingAddressLine2(),
                                paymentMethod.getPostalCode(),
                                paymentMethod.getCountry(),
                                paymentMethod.getPhoneNumber()
                        )).collect(Collectors.toList());
        return paymentMethodModels;
    }
}
