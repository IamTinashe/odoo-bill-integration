package com.zimworx.odoo_bill_integration.services.bill;

import com.zimworx.odoo_bill_integration.models.bill.invoiceResponse.Invoice;

import java.util.List;

public interface InvoiceService {
    List<Invoice> fetchInvoices();
}
