package com.nutrix.command.domain;

import com.nutrix.command.application.Notification;
import command.CreateBillC;
import events.BillCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Aggregate
public class BillA {

    @AggregateIdentifier
    private String id;
    private String patientId;
    private Date billDate;
    private Double amount;
    private String ruc;

    public BillA(){
    }

    @CommandHandler
    public BillA(CreateBillC createBillC){
        Notification notification = validateBill(createBillC);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        BillCreatedEvent event = new BillCreatedEvent(
                createBillC.getId(),
                createBillC.getPatientId(),
                createBillC.getBillDate(),
                createBillC.getAmount(),
                createBillC.getRuc()
        );
        apply(event);
    }

    private Notification validateBill(CreateBillC createBillC) {
        Notification notification = new Notification();
        validateBillId(createBillC.getId(), notification);
        return notification;
    }

    private void validateBillId(String billId, Notification notification) {
        if (billId == null) {
            notification.addError("Bill id is missing");
        }
    }

    //Event Sourcing Handlers

    @EventSourcingHandler
    public void on(BillCreatedEvent billCreatedEvent){
        this.id = billCreatedEvent.getId();
        this.patientId = billCreatedEvent.getPatientId();
        this.billDate = billCreatedEvent.getBillDate();
        this.amount = billCreatedEvent.getAmount();
        this.ruc = billCreatedEvent.getRuc();
    }
}
