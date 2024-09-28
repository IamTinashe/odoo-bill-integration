package com.zimworx.odoo_bill_integration.services.odoo.implementations;

import com.zimworx.odoo_bill_integration.config.OdooProperties;
import com.zimworx.odoo_bill_integration.exceptions.OdooServiceException;
import com.zimworx.odoo_bill_integration.models.odoo.OdooInvoice;
import com.zimworx.odoo_bill_integration.services.odoo.OdooAuthenticationService;
import com.zimworx.odoo_bill_integration.services.odoo.OdooInvoiceService;
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
public class OdooInvoiceServiceImpl implements OdooInvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(OdooCustomerServiceImpl.class);

    private final OdooProperties odooProperties;
    private final OdooAuthenticationService authenticationService;
    private final OdooXmlRpcUtils xmlRpcUtils;

    @Autowired
    public OdooInvoiceServiceImpl(
            OdooProperties odooProperties,
            OdooAuthenticationService authenticationService,
            OdooXmlRpcUtils xmlRpcUtils) {
        this.odooProperties = odooProperties;
        this.authenticationService = authenticationService;
        this.xmlRpcUtils = xmlRpcUtils;
    }


    @Override
    public List<OdooInvoice> fetchOdooInvoices() throws MalformedURLException, XmlRpcException {
        logger.info("Fetching clients from Odoo");
        try {
            return fetchInvoices("Draft");
        } catch (Exception e) {
            logger.error("Failed to fetch clients from Odoo: {}", e.getMessage(), e);
            throw new OdooServiceException("Failed to fetch clients from Odoo", e);
        }
    }

    private List<OdooInvoice> fetchInvoices(String state) throws MalformedURLException, XmlRpcException {
        XmlRpcClient client = xmlRpcUtils.createClient(odooProperties.getUrl(), "/xmlrpc/2/object");
        int uid = authenticationService.authenticate();
        List<Object> customers = asList((Object[]) client.execute("execute_kw", asList(
                odooProperties.getDb(), uid, odooProperties.getPassword(),
                "account.move", "search_read",
                asList(asList(asList("state", "=", state))),
                new HashMap<String, Object>() {{
                    put("fields", asList("id", "amount_paid", "residual_amount", "amount_total"));
                }}
        )));

        List<OdooInvoice> invoiceResponses = new ArrayList<>();
        for (Object invoice : customers) {
            invoiceResponses.add(xmlRpcUtils.convertInvoiceResponse((HashMap<String, Object>) invoice));
        }
        return invoiceResponses;
    }

    @Override
    public void postInvoice(OdooInvoice invoice) {
        //logger.info("Posting invoice to Odoo: {}", invoice.getInvoiceNumber());
        try {
            // Implement logic to post invoice via XML-RPC
            // ...
            logger.info("Invoice posted successfully");
        } catch (Exception e) {
            logger.error("Failed to post invoice to Odoo: {}", e.getMessage(), e);
            throw new OdooServiceException("Failed to post invoice to Odoo", e);
        }
    }
}
