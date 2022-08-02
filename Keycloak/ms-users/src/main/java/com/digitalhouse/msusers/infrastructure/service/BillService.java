package com.digitalhouse.msusers.infrastructure.service;

import com.digitalhouse.msusers.domain.models.Bill;
import com.digitalhouse.msusers.domain.repositories.BillsFeignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    private final BillsFeignRepository repository;

    @Autowired
    public BillService(BillsFeignRepository repository) {
        this.repository = repository;
    }

    public List<Bill> getBillsByCustomerBill(String customerBill) {
        return repository.getBillsByCustomerBill(customerBill);
    }

}
