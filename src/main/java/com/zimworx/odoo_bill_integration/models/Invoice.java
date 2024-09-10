package com.zimworx.odoo_bill_integration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    private String id;
    private String vendor;
    private double amount;
    private String date;
}
