package com.zimworx.odoo_bill_integration.services;

import com.zimworx.odoo_bill_integration.models.Invoice;

public interface OdooService {
    void postInvoice(Invoice invoice);
}
