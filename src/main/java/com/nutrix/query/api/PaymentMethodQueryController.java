package com.nutrix.query.api;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import queries.GetPaymentMethodsQuery;
import result.PaymentMethodResult;

import java.util.List;

@RestController
@RequestMapping("/paymentMethod")
@Api(tags="PaymentMethod", value = "Servicio Web RESTFul de Payment Methods")
public class PaymentMethodQueryController {
    private final QueryGateway queryGateway;

    @Autowired
    public PaymentMethodQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Búsqueda de todos los Payment methods", notes ="Método que busca todos los Payment methods" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Payment methods encontrados"),
            @ApiResponse(code=404, message = "Payment methods no encontrados")
    })
    public ResponseEntity<List<PaymentMethodResult>> getAll(){
        try{
            GetPaymentMethodsQuery getPaymentMethodsQuery = new GetPaymentMethodsQuery();
            List<PaymentMethodResult> paymentMethods = queryGateway.query(getPaymentMethodsQuery,
                            ResponseTypes.multipleInstancesOf(PaymentMethodResult.class))
                    .join();
            return new ResponseEntity<>(paymentMethods, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}