package com.zimworx.odoo_bill_integration.services.impl;

import com.zimworx.odoo_bill_integration.models.Invoice;
import com.zimworx.odoo_bill_integration.services.BillService;
import com.zimworx.odoo_bill_integration.services.MiddlewareService;
import com.zimworx.odoo_bill_integration.services.OdooService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiddlewareServiceImpl implements MiddlewareService {

    private final BillService billService;
    private final OdooService odooService;

    public MiddlewareServiceImpl(BillService billService, OdooService odooService) {
        this.billService = billService;
        this.odooService = odooService;
    }

    @Override
    public void syncInvoices() {
        List<Invoice> invoices = billService.fetchInvoices();
        for (Invoice invoice : invoices) {
            //odooService.postInvoice(invoice);
        }
    }
}
