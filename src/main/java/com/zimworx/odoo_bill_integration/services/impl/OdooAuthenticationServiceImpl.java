package com.zimworx.odoo_bill_integration.services.impl;

import com.zimworx.odoo_bill_integration.OdooBillIntegrationApplication;
import com.zimworx.odoo_bill_integration.config.OdooProperties;
import com.zimworx.odoo_bill_integration.services.OdooAuthenticationService;
import com.zimworx.odoo_bill_integration.utils.OdooXmlRpcUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;

@Service
public class OdooAuthenticationServiceImpl implements OdooAuthenticationService {

    private final OdooProperties odooProperties;

    final XmlRpcClient client = new XmlRpcClient();
    final XmlRpcClientConfigImpl common_config = new XmlRpcClientConfigImpl();

    private static final Logger logger = LoggerFactory.getLogger(OdooBillIntegrationApplication.class);

    public OdooAuthenticationServiceImpl(OdooProperties odooProperties, OdooXmlRpcUtils xmlRpcUtils) {
        this.odooProperties = odooProperties;
    }

    @Override
    public int authenticate() throws MalformedURLException, XmlRpcException {
        common_config.setServerURL(new URL(String.format("%s/xmlrpc/2/common", odooProperties.getUrl())));
        return (int) client.execute(common_config, "authenticate", asList(odooProperties.getDb(), odooProperties.getUsername(), odooProperties.getPassword(), emptyMap()));

    }
}
