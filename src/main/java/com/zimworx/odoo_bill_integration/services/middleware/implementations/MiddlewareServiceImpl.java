package com.zimworx.odoo_bill_integration.services.middleware.implementations;

import com.zimworx.odoo_bill_integration.errorhandlers.MiddlewareException;
import com.zimworx.odoo_bill_integration.models.bill.customerResponse.Customer;
import com.zimworx.odoo_bill_integration.models.bill.invoiceResponse.Invoice;
import com.zimworx.odoo_bill_integration.services.bill.CustomerService;
import com.zimworx.odoo_bill_integration.services.bill.InvoiceService;
import com.zimworx.odoo_bill_integration.services.middleware.MiddlewareService;
import com.zimworx.odoo_bill_integration.services.odoo.OdooService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiddlewareServiceImpl implements MiddlewareService {

    private static final Logger logger = LoggerFactory.getLogger(MiddlewareServiceImpl.class);

    private final InvoiceService invoiceService;
    private final CustomerService customerService;
    private final OdooService odooService;

    @Autowired
    public MiddlewareServiceImpl(
            InvoiceService invoiceService,
            CustomerService customerService,
            OdooService odooService) {
        this.invoiceService = invoiceService;
        this.customerService = customerService;
        this.odooService = odooService;
    }

    @Override
    public void syncInvoices() {
        logger.info("Starting invoice synchronization");
        try {
            List<Invoice> billInvoices = invoiceService.fetchBillInvoices();
            List<Customer> billCustomers = customerService.fetchBillCustomers();

            // Synchronize customers
            odooService.syncCustomers(customers);

            // Synchronize invoices
            for (Invoice invoice : invoices) {
                odooService.postInvoice(invoice);
            }
            logger.info("Invoice synchronization completed successfully");
        } catch (Exception e) {
            logger.error("Error during invoice synchronization: {}", e.getMessage(), e);
            throw new MiddlewareException("Error during invoice synchronization", e);
        }
    }
}

