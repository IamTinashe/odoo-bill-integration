package com.zimworx.odoo_bill_integration.services.odoo;

import com.zimworx.odoo_bill_integration.models.odoo.customerResponse.Customer;
import com.zimworx.odoo_bill_integration.models.odoo.invoiceResponse.Invoice;
import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;
import java.util.List;

public interface OdooInvoiceService {

    List<Invoice> fetchOdooInvoices() throws MalformedURLException, XmlRpcException;
    void postInvoice(Invoice invoice);
}
