package com.nutrix.command.api;

import com.nutrix.command.application.dto.ErrorResponseDto;
import com.nutrix.query.models.CreatePaymentMethodModel;
import command.CreatePaymentMethodC;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import result.PaymentMethodResult;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/paymentMethod")
@Api(tags="PaymentMethod", value = "Servicio Web RESTFul de PaymentMethod")
public class PaymentMethodCommandController {

    private final CommandGateway commandGateway;

    @Autowired
    public PaymentMethodCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    //Event Sourcing Post
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de un PaymentMethod", notes ="Método que registra un PaymentMethod" )
    @ApiResponses({
            @ApiResponse(code=201, message = "PaymentMethod creado"),
            @ApiResponse(code=404, message = "PaymentMethod no creado")
    })
    public ResponseEntity<Object> insertPaymentMethod(@Validated @RequestBody CreatePaymentMethodModel paymentMethod){
        String id = UUID.randomUUID().toString();
        CreatePaymentMethodC createPaymentMethodC = new CreatePaymentMethodC(
                id,
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
        );
        CompletableFuture<Object> future = commandGateway.send(createPaymentMethodC);
        CompletableFuture<Object> futureResponse = future.handle((ok, ex) -> {
            if (ex != null) {
                return new ErrorResponseDto(ex.getMessage());
            }
            return new PaymentMethodResult(
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
        });
        try {
            Object response = futureResponse.get();
            if (response instanceof PaymentMethodResult) {
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}