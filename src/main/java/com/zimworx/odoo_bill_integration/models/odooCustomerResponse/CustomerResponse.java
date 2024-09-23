package com.zimworx.odoo_bill_integration.models.odooCustomerResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private int id;
    private String name;
    private String city;
    private String email;
    private String phone;
}
