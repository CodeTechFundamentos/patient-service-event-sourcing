package com.nutrix.query.application.handlers;
import com.nutrix.command.infra.Bill;
import com.nutrix.command.infra.IBillRepository;
import com.nutrix.query.models.CreateBillModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import queries.GetBillsQuery;
import result.BillResult;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BillQueryHandler {
    private final IBillRepository billRepository;

    @Autowired
    public BillQueryHandler(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @QueryHandler
    public List<BillResult> handle(GetBillsQuery query) {
        List<Bill> bills = billRepository.findAll();

        List<BillResult> billModels =
                bills.stream()
                        .map(bill -> new BillResult(
                                bill.getId(),
                                bill.getPatientId(),
                                bill.getBillDate(),
                                bill.getAmount(),
                                bill.getRuc()
                        )).collect(Collectors.toList());
        return billModels;
    }
}
