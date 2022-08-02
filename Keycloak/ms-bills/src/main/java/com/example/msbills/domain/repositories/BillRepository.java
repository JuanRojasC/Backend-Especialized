package com.example.msbills.domain.repositories;

import com.example.msbills.domain.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, String> {

    public List<Bill> findBillsByCustomerBill(String customerBill);

}
