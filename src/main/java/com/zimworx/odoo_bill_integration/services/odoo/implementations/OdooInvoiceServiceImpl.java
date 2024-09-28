package com.zimworx.odoo_bill_integration.services.odoo.implementations;

import com.zimworx.odoo_bill_integration.config.OdooProperties;
import com.zimworx.odoo_bill_integration.errorhandlers.OdooServiceException;
import com.zimworx.odoo_bill_integration.models.odoo.invoiceResponse.Invoice;
import com.zimworx.odoo_bill_integration.services.odoo.OdooAuthenticationService;
import com.zimworx.odoo_bill_integration.services.odoo.OdooInvoiceService;
import com.zimworx.odoo_bill_integration.utils.OdooXmlRpcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
    public void postInvoice(Invoice invoice) {
        logger.info("Posting invoice to Odoo: {}", invoice.getInvoiceNumber());
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
