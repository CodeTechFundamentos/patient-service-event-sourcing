package com.nutrix.command.domain;

import com.nutrix.command.application.Notification;
import command.CreatePatientC;
import events.PatientCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Aggregate
public class PatientA {

    @AggregateIdentifier
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date createdAt;

    public PatientA(){
    }

    @CommandHandler
    public PatientA(CreatePatientC createPatientC){
        Notification notification = validatePatient(createPatientC);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        PatientCreatedEvent event = new PatientCreatedEvent(
                createPatientC.getId(),
                createPatientC.getUsername(),
                createPatientC.getPassword(),
                createPatientC.getFirstName(),
                createPatientC.getLastName(),
                createPatientC.getEmail(),
                createPatientC.getCreatedAt()
        );
        apply(event);
    }

    private Notification validatePatient(CreatePatientC createPatientC) {
        Notification notification = new Notification();
        validatePatientId(createPatientC.getId(), notification);
        return notification;
    }

    private void validatePatientId(String patientId, Notification notification) {
        if (patientId == null) {
            notification.addError("Patient id is missing");
        }
    }

    //Event Sourcing Handlers

    @EventSourcingHandler
    public void on(PatientCreatedEvent patientCreatedEvent){
        this.id = patientCreatedEvent.getId();
        this.username = patientCreatedEvent.getUsername();
        this.password = patientCreatedEvent.getPassword();
        this.firstName = patientCreatedEvent.getFirstName();
        this.lastName = patientCreatedEvent.getLastName();
        this.email = patientCreatedEvent.getEmail();
        this.createdAt = patientCreatedEvent.getCreatedAt();
    }
}
