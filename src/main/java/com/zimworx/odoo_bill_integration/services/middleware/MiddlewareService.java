package com.zimworx.odoo_bill_integration.services.middleware;

import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;

public interface MiddlewareService {
    void syncInvoices() throws MalformedURLException, XmlRpcException;
}
