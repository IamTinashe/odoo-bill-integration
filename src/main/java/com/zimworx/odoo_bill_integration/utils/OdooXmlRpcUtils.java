package com.zimworx.odoo_bill_integration.utils;

import com.zimworx.odoo_bill_integration.models.CustomerResponse;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@Component
public class OdooXmlRpcUtils {

    public XmlRpcClient createClient(String url, String endpoint) throws MalformedURLException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(url + endpoint));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        return client;
    }

    public CustomerResponse convertCustomerResponse(HashMap<String, Object> customerMap) {
        CustomerResponse customer = new CustomerResponse();
        customer.setId((Integer) customerMap.get("id"));
        customer.setName((String) customerMap.get("name"));
        customer.setCity((String) customerMap.get("city"));
        customer.setEmail((String) customerMap.get("email"));
        customer.setPhone((String) customerMap.get("phone"));
        return customer;
    }
}

