package com.nutrix.command.application.handlers;

import com.nutrix.command.infra.IPatientRepository;
import com.nutrix.command.infra.Patient;
import events.PatientCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("patient")
public class PatientEventHandler {
    private final IPatientRepository patientRepository;

    @Autowired
    public PatientEventHandler(IPatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @EventHandler
    public void on(PatientCreatedEvent event){
        patientRepository.save(new Patient(
                        event.getId(),
                        event.getUsername(),
                        event.getEmail(),
                        event.getPassword(),
                        event.getFirstName(),
                        event.getLastName(),
                        event.getCreatedAt()
                )
        );
    }
}
