package com.example.msbills.application.rest.controller;

import com.example.msbills.domain.models.Bill;
import com.example.msbills.infrastructure.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Context;
import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService service;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Bill>> getAll() {
        return ResponseEntity.ok().body(service.getAllBill());
    }

    @GetMapping("/{customerBill}")
    public ResponseEntity<List<Bill>> getBillsByCustomerBill(@PathVariable String customerBill) {
        return ResponseEntity.ok().body(service.getBillsByCustomerBill(customerBill));
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('GROUP_PROVIDERS')")
    public ResponseEntity<Bill> create(@RequestBody Bill bill){
        return ResponseEntity.ok().body(service.save(bill, SecurityContextHolder.getContext()));
    }

}
