package com.nutrix.query.models;
import lombok.Value;
import java.util.Date;

@Value
public class UpdateBillModel {
    private String id;
    private Double amount;
    private String ruc;
}
