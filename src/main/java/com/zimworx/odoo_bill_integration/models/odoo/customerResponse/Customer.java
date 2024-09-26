package com.zimworx.odoo_bill_integration.models.odoo.customerResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @NonNull
    private int id;
    private String name;
    private String city;
    private String email;
    private String phone;
}
