package com.zimworx.odoo_bill_integration.utils;

import com.zimworx.odoo_bill_integration.models.odoo.OdooCustomer;
import com.zimworx.odoo_bill_integration.models.odoo.OdooInvoice;
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

    private Helpers helpers = new Helpers();

    public XmlRpcClient createClient(String url, String endpoint) throws MalformedURLException {
        logger.debug("Creating XML-RPC client for URL: {}{}", url, endpoint);
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(url + endpoint));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        return client;
    }

    public OdooCustomer convertCustomerResponse(Map<String, Object> customerMap) {
        OdooCustomer customer = new OdooCustomer();
        customer.setId((Integer) customerMap.getOrDefault("id", 0));
        customer.setName(helpers.getStringValue(customerMap.getOrDefault("name", "")));
        customer.setCity(helpers.getStringValue(customerMap.getOrDefault("city", "")));
        customer.setEmail(helpers.getStringValue(customerMap.getOrDefault("email", "")));
        customer.setPhone(helpers.getStringValue(customerMap.getOrDefault("phone", "")));
        logger.debug("Converted customer: {}", customer);
        return customer;
    }

    public OdooInvoice convertInvoiceResponse(Map<String, Object> invoiceMap){
        OdooInvoice invoice = new OdooInvoice();
        invoice.setId((Integer) invoiceMap.getOrDefault("id", 0));
        invoice.setCustomerName(helpers.getStringValue(helpers.convertMany2oneName(invoiceMap.get("partner_id"))));
        invoice.setCustomerId(helpers.convertMany2oneID(invoiceMap.get("partner_id")));
        invoice.setAmount_paid((Double) invoiceMap.getOrDefault("amount_paid", ""));
        invoice.setAmount_residual((Double) invoiceMap.getOrDefault("amount_residual", 0));
        invoice.setAmount_total((Double) invoiceMap.getOrDefault("amount_total", 0));

        logger.debug("Converted invoice: {}", invoice);
        return invoice;
    }
}
