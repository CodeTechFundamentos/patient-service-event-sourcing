package com.nutrix.command.infra;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String id;
    @Column(name = "patientId")
    private String patientId;
    @Column(name="bill_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date billDate;
    @Column(name="amount")
    private Double amount;
    @Column(name="ruc")
    private String ruc;
}

