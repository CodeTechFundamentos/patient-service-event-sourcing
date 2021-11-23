package com.nutrix.command.api;

import com.nutrix.command.application.dto.ErrorResponseDto;
import com.nutrix.command.infra.Bill;
import com.nutrix.command.infra.Patient;
import com.nutrix.query.models.CreatePatientModel;
import command.CreatePatientC;
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
import result.PatientResult;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/patient")
@Api(tags="Patient", value = "Servicio Web RESTFul de Patient")
public class PatientCommandController {

    private final CommandGateway commandGateway;

    @Autowired
    public PatientCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    //Event Sourcing Post
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de un Patient", notes ="Método que registra un Patient" )
    @ApiResponses({
            @ApiResponse(code=200, message = "La operación fue exitosa", response = Patient.class),
            @ApiResponse(code=201, message = "Patient creado", response = Patient.class),
            @ApiResponse(code=401, message = "Es necesario autenticar para ejecutar la solicitud"),
            @ApiResponse(code=403, message = "El cliente no posee los permisos necesarios"),
            @ApiResponse(code=404, message = "Patient no fue creado")
    })
    public ResponseEntity<Object> insertPatient(@Validated @RequestBody CreatePatientModel patient){
        String id = UUID.randomUUID().toString();
        CreatePatientC createPatientC = new CreatePatientC(
                id,
                patient.getUsername(),
                patient.getPassword(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getEmail(),
                patient.getCreatedAt()
        );
        CompletableFuture<Object> future = commandGateway.send(createPatientC);
        CompletableFuture<Object> futureResponse = future.handle((ok, ex) -> {
            if (ex != null) {
                return new ErrorResponseDto(ex.getMessage());
            }
            return new PatientResult(
                    createPatientC.getId(),
                    createPatientC.getUsername(),
                    createPatientC.getPassword(),
                    createPatientC.getFirstName(),
                    createPatientC.getLastName(),
                    createPatientC.getEmail(),
                    createPatientC.getCreatedAt()
            );
        });
        try {
            Object response = futureResponse.get();
            if (response instanceof PatientResult) {
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}