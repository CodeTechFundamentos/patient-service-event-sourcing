package com.nutrix.query.models;
import lombok.Value;
import java.util.Date;

@Value
public class CreateBillModel {
    private String id;
    private String patientId;
    private Date billDate;
    private Double amount;
    private String ruc;
}
