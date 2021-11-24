package com.nutrix.query.api;
import com.nutrix.command.infra.Bill;
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
import queries.GetBillsQuery;
import result.BillResult;

import java.util.List;

@RestController
@RequestMapping("/bill")
@Api(tags="Bill", value = "Servicio Web RESTFul de Bill")
public class BillQueryController {
    private final QueryGateway queryGateway;

    @Autowired
    public BillQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Búsqueda de todos los Bill", notes ="Método que busca todos los Bills" )
    @ApiResponses({
            @ApiResponse(code=200, message = "La operación fue exitosa", response = Bill.class),
            @ApiResponse(code=201, message = "Bills encontrados", response = Bill.class),
            @ApiResponse(code=401, message = "Es necesario autenticar para obtener la respuesta solicitada"),
            @ApiResponse(code=403, message = "El cliente no posee los permisos necesarios"),
            @ApiResponse(code=404, message = "Bills no encontrados")
    })
    public ResponseEntity<List<BillResult>> getAll(){
        try{
            GetBillsQuery getBillsQuery = new GetBillsQuery();
            List<BillResult> bills = queryGateway.query(getBillsQuery,
                            ResponseTypes.multipleInstancesOf(BillResult.class))
                    .join();
            return new ResponseEntity<>(bills, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
