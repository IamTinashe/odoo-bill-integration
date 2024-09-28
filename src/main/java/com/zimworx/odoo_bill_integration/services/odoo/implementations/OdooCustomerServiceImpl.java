package com.zimworx.odoo_bill_integration.services.odoo.implementations;

import com.zimworx.odoo_bill_integration.config.OdooProperties;
import com.zimworx.odoo_bill_integration.errorhandlers.OdooServiceException;
import com.zimworx.odoo_bill_integration.models.odoo.customerResponse.Customer;
import com.zimworx.odoo_bill_integration.models.odoo.invoiceResponse.Invoice;
import com.zimworx.odoo_bill_integration.services.odoo.OdooAuthenticationService;
import com.zimworx.odoo_bill_integration.services.odoo.OdooCustomerService;
import com.zimworx.odoo_bill_integration.utils.OdooXmlRpcUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class OdooCustomerServiceImpl implements OdooCustomerService {

    private static final Logger logger = LoggerFactory.getLogger(OdooCustomerServiceImpl.class);

    private final OdooProperties odooProperties;
    private final OdooAuthenticationService authenticationService;
    private final OdooXmlRpcUtils xmlRpcUtils;

    @Autowired
    public OdooCustomerServiceImpl(
            OdooProperties odooProperties,
            OdooAuthenticationService authenticationService,
            OdooXmlRpcUtils xmlRpcUtils) {
        this.odooProperties = odooProperties;
        this.authenticationService = authenticationService;
        this.xmlRpcUtils = xmlRpcUtils;
    }

    @Override
    public List<Customer> fetchOdooCustomers() throws MalformedURLException, XmlRpcException {
        logger.info("Fetching clients from Odoo");
        try {
            return fetchCustomers("Active Customer");
        } catch (Exception e) {
            logger.error("Failed to fetch clients from Odoo: {}", e.getMessage(), e);
            throw new OdooServiceException("Failed to fetch clients from Odoo", e);
        }
    }

    private List<Customer> fetchCustomers(String customerStage) throws MalformedURLException, XmlRpcException {
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

        List<Customer> customerResponses = new ArrayList<>();
        for (Object customer : customers) {
            customerResponses.add(xmlRpcUtils.convertCustomerResponse((HashMap<String, Object>) customer));
        }
        return customerResponses;
    }
}
