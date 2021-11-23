package com.nutrix.command.infra;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Bill")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill{
    @Id
    @ApiModelProperty(notes = "Bill Id",name="billtId",required=true,example = "04e19ea0-d9e4-4fa2-8cc8-6b7adc47bb71")
    private String id;
    @Column(name = "patientId")
    @ApiModelProperty(notes = "Patient Id",name="patientId",required=true,example = "88c061a3-b67c-48eb-a63d-49d5bdf9e836")
    private String patientId;
    @Column(name="bill_date")
    @ApiModelProperty(notes = "Bill date",name="billDate",required=true,example = "2021-11-23T04:25:17.917+00:00")
    @Temporal(TemporalType.TIMESTAMP)
    private Date billDate;
    @Column(name="amount")
    @ApiModelProperty(notes = "Bill amount",name="amount",required=true,example = "100")
    private Double amount;
    @Column(name="ruc")
    @ApiModelProperty(notes = "RUC",name="ruc",required=true,example = "20100028579")
    private String ruc;
}

