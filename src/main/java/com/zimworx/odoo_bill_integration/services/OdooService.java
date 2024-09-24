package com.zimworx.odoo_bill_integration.services;

import com.zimworx.odoo_bill_integration.models.odoo.customerResponse.Customer;
import com.zimworx.odoo_bill_integration.models.bill.invoiceResponse.Invoice;
import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;
import java.util.List;

public interface OdooService {
    void postInvoice(Invoice invoice);
    List<Customer> getClients() throws MalformedURLException, XmlRpcException;
}
