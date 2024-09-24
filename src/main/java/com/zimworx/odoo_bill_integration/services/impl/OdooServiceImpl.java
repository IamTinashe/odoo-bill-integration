package com.zimworx.odoo_bill_integration.services.impl;

import com.zimworx.odoo_bill_integration.config.OdooProperties;
import com.zimworx.odoo_bill_integration.models.invoiceResponse.Invoice;
import com.zimworx.odoo_bill_integration.models.odooCustomerResponse.CustomerResponse;
import com.zimworx.odoo_bill_integration.services.OdooAuthenticationService;
import com.zimworx.odoo_bill_integration.services.OdooService;
import com.zimworx.odoo_bill_integration.utils.OdooXmlRpcUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class OdooServiceImpl implements OdooService {

    private final RestTemplate restTemplate;
    private final String odooApiUrl;

    private final OdooProperties odooProperties;
    private final OdooAuthenticationService authenticationService;
    private final OdooXmlRpcUtils xmlRpcUtils;


    @Autowired
    public OdooServiceImpl(RestTemplate restTemplate, @Value("${odoo.api.url}") String odooApiUrl, OdooProperties odooProperties, OdooAuthenticationService authenticationService, OdooXmlRpcUtils xmlRpcUtils) {
        this.restTemplate = restTemplate;
        this.odooApiUrl = odooApiUrl;
        this.odooProperties = odooProperties;
        this.authenticationService = authenticationService;
        this.xmlRpcUtils = xmlRpcUtils;
    }

    @Override
    public void postInvoice(Invoice invoice) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Invoice> request = new HttpEntity<>(invoice, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                odooApiUrl + "/invoices",
                HttpMethod.POST,
                request,
                String.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to post invoice to Odoo");
        }
    }

    @Override
    public List<CustomerResponse> getClients() throws MalformedURLException, XmlRpcException {
        return fetchCustomers("Active Customer");
    }

    private List<CustomerResponse> fetchCustomers(String customerStage) throws MalformedURLException, XmlRpcException {
        XmlRpcClient client = xmlRpcUtils.createClient(odooProperties.getUrl(), "/xmlrpc/2/object");
        int uid = authenticationService.authenticate();
        List<Object> customers = asList((Object[]) client.execute("execute_kw", asList(
                odooProperties.getDb(), uid, odooProperties.getPassword(),
                "res.partner", "search_read",
                asList(asList(asList("x_studio_customer_stage", "=", customerStage))),
                new HashMap<String, Object>() {{
                    put("fields", asList("id", "name", "city", "email"));
                }}
        )));

        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (Object customer : customers) {
            customerResponses.add(xmlRpcUtils.convertCustomerResponse((HashMap<String, Object>) customer));
        }
        return customerResponses;
    }
}