package com.zimworx.odoo_bill_integration.services;

import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;

public interface OdooAuthenticationService {
    int authenticate() throws MalformedURLException, XmlRpcException;
}
