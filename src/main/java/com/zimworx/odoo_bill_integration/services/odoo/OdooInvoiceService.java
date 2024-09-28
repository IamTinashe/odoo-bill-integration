package com.zimworx.odoo_bill_integration.services.odoo;

import com.zimworx.odoo_bill_integration.models.odoo.OdooInvoice;
import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;
import java.util.List;

public interface OdooInvoiceService {

    List<OdooInvoice> fetchOdooInvoices() throws MalformedURLException, XmlRpcException;
    void postInvoice(OdooInvoice invoice);
}
