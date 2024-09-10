package com.zimworx.odoo_bill_integration.services;

import com.zimworx.odoo_bill_integration.models.Invoice;
import com.zimworx.odoo_bill_integration.models.Response;

import java.util.List;

public interface BillService {
    List<Invoice> fetchInvoices();
}