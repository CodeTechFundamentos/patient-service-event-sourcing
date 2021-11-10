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
import queries.GetPatientsQuery;
import result.PatientResult;

import java.util.List;

@RestController
@RequestMapping("/patient")
@Api(tags="Patient", value = "Servicio Web RESTFul de Patient")
public class PatientQueryController {
    private final QueryGateway queryGateway;

    @Autowired
    public PatientQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Búsqueda de todos los Patient", notes ="Método que busca todos los Patients" )
    @ApiResponses({
            @ApiResponse(code=201, message = "Patients encontrados"),
            @ApiResponse(code=404, message = "Patients no encontrados")
    })
    public ResponseEntity<List<PatientResult>> getAll(){
        try{
            GetPatientsQuery getPatientsQuery = new GetPatientsQuery();
            List<PatientResult> patients = queryGateway.query(getPatientsQuery,
                            ResponseTypes.multipleInstancesOf(PatientResult.class))
                    .join();
            return new ResponseEntity<>(patients, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
