package com.zimworx.odoo_bill_integration.services.bill;

import com.zimworx.odoo_bill_integration.models.bill.customerResponse.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> fetchBillCustomers();
}
