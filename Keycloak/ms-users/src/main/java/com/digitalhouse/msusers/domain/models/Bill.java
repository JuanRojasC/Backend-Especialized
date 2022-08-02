package com.digitalhouse.msusers.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Bill {

    private String idBill;

    private String customerBill;

    private String productBill;

    private Double totalPrice;

}
