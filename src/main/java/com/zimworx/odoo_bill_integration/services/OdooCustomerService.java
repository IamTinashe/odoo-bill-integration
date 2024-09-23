package com.zimworx.odoo_bill_integration.services;

import com.zimworx.odoo_bill_integration.models.odooCustomerResponse.CustomerResponse;
import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;
import java.util.List;

public interface OdooCustomerService {
    List<CustomerResponse> getClients() throws MalformedURLException, XmlRpcException;
}
