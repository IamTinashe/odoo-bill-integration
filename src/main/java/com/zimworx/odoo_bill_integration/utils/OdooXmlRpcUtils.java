package com.zimworx.odoo_bill_integration.utils;

import com.zimworx.odoo_bill_integration.models.odoo.customerResponse.Customer;
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

    public Customer convertCustomerResponse(HashMap<String, Object> customerMap) {
        Customer customer = new Customer();
        customer.setId((Integer) customerMap.getOrDefault("id", 0));
        customer.setName(customerMap.get("name") != null ? customerMap.get("name").toString() : "");
        customer.setCity(customerMap.get("city") != null ? customerMap.get("city").toString() : "");
        customer.setEmail(customerMap.get("email") != null ? customerMap.get("email").toString() : "");
        customer.setPhone(customerMap.get("phone") != null ? customerMap.get("phone").toString() : "");

        return customer;
    }

}

