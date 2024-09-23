package com.zimworx.odoo_bill_integration.services;

import com.zimworx.odoo_bill_integration.models.customerResponse.Customer;
import com.zimworx.odoo_bill_integration.models.invoiceResponse.Invoice;

import java.util.List;

public interface BillService {
    List<Invoice> fetchInvoices();
    List<Customer> fetchCustomers();
}