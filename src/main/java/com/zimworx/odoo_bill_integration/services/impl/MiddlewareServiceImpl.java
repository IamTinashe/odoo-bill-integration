package com.zimworx.odoo_bill_integration.services.impl;

import com.zimworx.odoo_bill_integration.models.customerResponse.Customer;
import com.zimworx.odoo_bill_integration.models.invoiceResponse.Invoice;
import com.zimworx.odoo_bill_integration.services.BillService;
import com.zimworx.odoo_bill_integration.services.MiddlewareService;
import com.zimworx.odoo_bill_integration.services.OdooService;
import org.apache.xmlrpc.XmlRpcException;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
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
    public void syncInvoices() throws MalformedURLException, XmlRpcException {
        List<Invoice> invoices = billService.fetchInvoices();
        List<Customer> customers  = billService.fetchCustomers();
        System.out.println(customers);
        System.out.println(odooService.getClients());
        for (Invoice invoice : invoices) {
            //odooService.postInvoice(invoice);
        }
    }
}
