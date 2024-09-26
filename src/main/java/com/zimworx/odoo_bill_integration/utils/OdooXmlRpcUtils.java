package com.zimworx.odoo_bill_integration.utils;

import com.zimworx.odoo_bill_integration.models.odoo.customerResponse.Customer;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class OdooXmlRpcUtils {

    private static final Logger logger = LoggerFactory.getLogger(OdooXmlRpcUtils.class);

    public XmlRpcClient createClient(String url, String endpoint) throws MalformedURLException {
        logger.debug("Creating XML-RPC client for URL: {}{}", url, endpoint);
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(url + endpoint));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        return client;
    }

    public Customer convertCustomerResponse(Map<String, Object> customerMap) {
        Customer customer = new Customer();
        customer.setId((Integer) customerMap.getOrDefault("id", 0));
        customer.setName((String) customerMap.getOrDefault("name", ""));
        customer.setCity((String) customerMap.getOrDefault("city", ""));
        customer.setEmail((String) customerMap.getOrDefault("email", ""));
        customer.setPhone((String) customerMap.getOrDefault("phone", ""));
        logger.debug("Converted customer: {}", customer);
        return customer;
    }
}
