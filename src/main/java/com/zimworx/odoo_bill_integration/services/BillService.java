package com.zimworx.odoo_bill_integration.services;

import com.zimworx.odoo_bill_integration.models.bill.customerResponse.Customer;
import com.zimworx.odoo_bill_integration.models.bill.invoiceResponse.Invoice;

import java.util.List;

public interface BillService {
    List<Invoice> fetchInvoices();
    List<Customer> fetchCustomers();
}