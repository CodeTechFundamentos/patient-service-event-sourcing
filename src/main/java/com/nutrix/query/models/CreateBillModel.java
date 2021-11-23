package com.nutrix.query.models;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;
import java.util.Date;

@Value
public class CreateBillModel {
    @ApiModelProperty(notes = "Patient Id",name="patientId",required=true,example = "88c061a3-b67c-48eb-a63d-49d5bdf9e836")
    private String patientId;
    private Date billDate;
    @ApiModelProperty(notes = "Bill amount",name="amount",required=true,example = "100")
    private Double amount;
    @ApiModelProperty(notes = "RUC",name="ruc",required=true,example = "20100028579")
    private String ruc;
}
