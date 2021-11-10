package com.nutrix.query.application.handlers;
import com.nutrix.command.infra.IPatientRepository;
import com.nutrix.command.infra.Patient;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import queries.GetPatientsQuery;
import result.PatientResult;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientQueryHandler {
    private final IPatientRepository patientRepository;

    @Autowired
    public PatientQueryHandler(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @QueryHandler
    public List<PatientResult> handle(GetPatientsQuery query) {
        List<Patient> patients = patientRepository.findAll();

        List<PatientResult> patientModels =
                patients.stream()
                        .map(patient -> new PatientResult(
                                patient.getId(),
                                patient.getUsername(),
                                patient.getPassword(),
                                patient.getFirstName(),
                                patient.getLastName(),
                                patient.getEmail(),
                                patient.getCreatedAt()
                        )).collect(Collectors.toList());
        return patientModels;
    }
}
