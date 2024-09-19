package com.zimworx.odoo_bill_integration.services.impl;

import com.zimworx.odoo_bill_integration.config.OdooProperties;
import com.zimworx.odoo_bill_integration.models.CustomerResponse;
import com.zimworx.odoo_bill_integration.services.OdooAuthenticationService;
import com.zimworx.odoo_bill_integration.services.OdooCustomerService;
import com.zimworx.odoo_bill_integration.utils.OdooXmlRpcUtils;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class OdooCustomerServiceImpl implements OdooCustomerService {

    private final OdooProperties odooProperties;
    private final OdooAuthenticationService authenticationService;
    private final OdooXmlRpcUtils xmlRpcUtils;

    public OdooCustomerServiceImpl(OdooProperties odooProperties, OdooAuthenticationService authenticationService, OdooXmlRpcUtils xmlRpcUtils) {
        this.odooProperties = odooProperties;
        this.authenticationService = authenticationService;
        this.xmlRpcUtils = xmlRpcUtils;
    }

    @Override
    public List<CustomerResponse> getActiveCustomers() throws MalformedURLException, XmlRpcException {
        return fetchCustomers("Active Customer");
    }

    @Override
    public List<CustomerResponse> getInactiveCustomers() throws MalformedURLException, XmlRpcException {
        return fetchCustomers("Inactive");
    }

    private List<CustomerResponse> fetchCustomers(String customerStage) throws MalformedURLException, XmlRpcException {
        XmlRpcClient client = xmlRpcUtils.createClient(odooProperties.getUrl(), "/xmlrpc/2/object");
        int uid = authenticationService.authenticate();
        List<Object> customers = asList((Object[]) client.execute("execute_kw", asList(
                odooProperties.getDb(), uid, odooProperties.getPassword(),
                "res.partner", "search_read",
                asList(asList(asList("x_studio_customer_stage", "=", customerStage))),
                new HashMap<String, Object>() {{
                    put("fields", asList("id", "name", "city", "email", "phone"));
                }}
        )));

        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (Object customer : customers) {
            customerResponses.add(xmlRpcUtils.convertCustomerResponse((HashMap<String, Object>) customer));
        }
        return customerResponses;
    }
}
