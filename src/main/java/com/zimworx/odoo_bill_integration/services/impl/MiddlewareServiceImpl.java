package com.zimworx.odoo_bill_integration.services.impl;

import com.zimworx.odoo_bill_integration.models.Invoice;
import com.zimworx.odoo_bill_integration.services.BillService;
import com.zimworx.odoo_bill_integration.services.MiddlewareService;
import com.zimworx.odoo_bill_integration.services.OdooCustomerService;
import com.zimworx.odoo_bill_integration.services.OdooService;
import org.apache.xmlrpc.XmlRpcException;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;

@Service
public class MiddlewareServiceImpl implements MiddlewareService {

    private final BillService billService;
    private final OdooCustomerService odooService;

    public MiddlewareServiceImpl(BillService billService, OdooCustomerService odooService) {
        this.billService = billService;
        this.odooService = odooService;
    }

    @Override
    public void syncInvoices() throws MalformedURLException, XmlRpcException {
        List<Invoice> invoices = billService.fetchInvoices();
        System.out.println(odooService.getClients());
        for (Invoice invoice : invoices) {
            //odooService.postInvoice(invoice);
        }
    }
}
