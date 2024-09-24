package com.zimworx.odoo_bill_integration.models.bill.customerResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    public Integer response_status;
    public String response_message;
    public ArrayList<Customer> response_data;
}

