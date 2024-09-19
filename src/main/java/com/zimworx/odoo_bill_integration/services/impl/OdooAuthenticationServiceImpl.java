package com.zimworx.odoo_bill_integration.services.impl;

import com.zimworx.odoo_bill_integration.config.OdooProperties;
import com.zimworx.odoo_bill_integration.services.OdooAuthenticationService;
import com.zimworx.odoo_bill_integration.utils.OdooXmlRpcUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;

@Service
public class OdooAuthenticationServiceImpl implements OdooAuthenticationService {

    private final OdooProperties odooProperties;
    private final OdooXmlRpcUtils xmlRpcUtils;

    public OdooAuthenticationServiceImpl(OdooProperties odooProperties, OdooXmlRpcUtils xmlRpcUtils) {
        this.odooProperties = odooProperties;
        this.xmlRpcUtils = xmlRpcUtils;
    }

    @Override
    public int authenticate() throws MalformedURLException, XmlRpcException {
        XmlRpcClient client = xmlRpcUtils.createClient(odooProperties.getUrl(), "/xmlrpc/2/common");
        return (int) client.execute("authenticate", asList(
                odooProperties.getDb(),
                odooProperties.getUsername(),
                odooProperties.getPassword(),
                emptyMap()
        ));
    }
}
