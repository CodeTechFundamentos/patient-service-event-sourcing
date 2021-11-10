package com.nutrix.command.application.handlers;

import com.nutrix.command.infra.Bill;
import com.nutrix.command.infra.IBillRepository;
import events.BillCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("bill")
public class BillEventHandler {
    private final IBillRepository billRepository;

    @Autowired
    public BillEventHandler(IBillRepository billRepository){
        this.billRepository = billRepository;
    }

    @EventHandler
    public void on(BillCreatedEvent event){
        billRepository.save(new Bill(
                        event.getId(),
                        event.getPatientId(),
                        event.getBillDate(),
                        event.getAmount(),
                        event.getRuc()
                )
        );
    }
}
