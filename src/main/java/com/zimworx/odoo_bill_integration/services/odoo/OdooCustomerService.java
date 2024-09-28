package com.zimworx.odoo_bill_integration.services.odoo;



import com.zimworx.odoo_bill_integration.models.odoo.OdooCustomer;
import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;
import java.util.List;

public interface OdooCustomerService {
    List<OdooCustomer> fetchOdooCustomers() throws MalformedURLException, XmlRpcException;
}

