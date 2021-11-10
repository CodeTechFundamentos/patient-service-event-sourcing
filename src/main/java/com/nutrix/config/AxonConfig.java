package com.nutrix.config;
import com.nutrix.command.domain.BillA;
import com.nutrix.command.domain.PatientA;
import com.nutrix.command.domain.PaymentMethodA;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Bean
    public Repository<PatientA> eventSourcingRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(PatientA.class)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public Repository<BillA> eventSourcingRepository2(EventStore eventStore) {
        return EventSourcingRepository.builder(BillA.class)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public Repository<PaymentMethodA> eventSourcingRepository3(EventStore eventStore) {
        return EventSourcingRepository.builder(PaymentMethodA.class)
                .eventStore(eventStore)
                .build();
    }
}