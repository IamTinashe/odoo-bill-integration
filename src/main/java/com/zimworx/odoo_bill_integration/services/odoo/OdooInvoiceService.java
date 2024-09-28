package com.zimworx.odoo_bill_integration.services.odoo;

import com.zimworx.odoo_bill_integration.models.odoo.invoiceResponse.Invoice;

public interface OdooInvoiceService {
    void postInvoice(Invoice invoice);
}
