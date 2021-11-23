package com.nutrix.command.api;

import com.nutrix.command.application.dto.ErrorResponseDto;
import com.nutrix.command.infra.Bill;
import com.nutrix.query.models.CreateBillModel;
import command.CreateBillC;
import io.swagger.annotations.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import result.BillResult;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/bill")
@Api(tags="Bill", value = "Servicio Web RESTFul de Bill")
public class BillCommandController {

    private final CommandGateway commandGateway;

    @Autowired
    public BillCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    //Event Sourcing Post
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de un Bill", notes ="Método que registra un Bill" )
    @ApiResponses({
            @ApiResponse(code=200, message = "La operación fue exitosa", response = Bill.class),
            @ApiResponse(code=201, message = "Bill creado", response = Bill.class),
            @ApiResponse(code=401, message = "Es necesario autenticar para ejecutar la solicitud"),
            @ApiResponse(code=403, message = "El cliente no posee los permisos necesarios"),
            @ApiResponse(code=404, message = "Bill no fue creado")
    })
    public ResponseEntity<Object> insertBill(@Validated @RequestBody CreateBillModel bill){
        String id = UUID.randomUUID().toString();
        CreateBillC createBillC = new CreateBillC(
                id,
                bill.getPatientId(),
                bill.getBillDate(),
                bill.getAmount(),
                bill.getRuc()
        );
        CompletableFuture<Object> future = commandGateway.send(createBillC);
        CompletableFuture<Object> futureResponse = future.handle((ok, ex) -> {
            if (ex != null) {
                return new ErrorResponseDto(ex.getMessage());
            }
            return new BillResult(
                    createBillC.getId(),
                    createBillC.getPatientId(),
                    createBillC.getBillDate(),
                    createBillC.getAmount(),
                    createBillC.getRuc()
            );
        });
        try {
            Object response = futureResponse.get();
            if (response instanceof BillResult) {
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}