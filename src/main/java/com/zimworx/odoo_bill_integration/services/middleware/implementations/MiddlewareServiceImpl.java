package com.zimworx.odoo_bill_integration.services.middleware.implementations;

import com.zimworx.odoo_bill_integration.exceptions.MiddlewareException;
import com.zimworx.odoo_bill_integration.models.bill.customerResponse.Customer;
import com.zimworx.odoo_bill_integration.models.bill.invoiceResponse.Invoice;
import com.zimworx.odoo_bill_integration.models.odoo.OdooCustomer;
import com.zimworx.odoo_bill_integration.models.odoo.OdooInvoice;
import com.zimworx.odoo_bill_integration.services.bill.CustomerService;
import com.zimworx.odoo_bill_integration.services.bill.InvoiceService;
import com.zimworx.odoo_bill_integration.services.middleware.MiddlewareService;
import com.zimworx.odoo_bill_integration.services.odoo.OdooCustomerService;
import com.zimworx.odoo_bill_integration.services.odoo.OdooInvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MiddlewareServiceImpl implements MiddlewareService {

    private static final Logger logger = LoggerFactory.getLogger(MiddlewareServiceImpl.class);

    private final InvoiceService invoiceService;
    private final CustomerService customerService;
    private final OdooCustomerService odooCustomerService;
    private final OdooInvoiceService odooInvoiceService;

    @Autowired
    public MiddlewareServiceImpl(InvoiceService invoiceService, CustomerService customerService, OdooCustomerService odooCustomerService, OdooInvoiceService odooInvoiceService) {
        this.invoiceService = invoiceService;
        this.customerService = customerService;
        this.odooCustomerService = odooCustomerService;
        this.odooInvoiceService = odooInvoiceService;
    }

    @Override
    public void syncInvoices() {
        logger.info("Starting invoice synchronization");
        try {
            List<Invoice> billInvoices = invoiceService.fetchBillInvoices();
            List<Customer> billCustomers = customerService.fetchBillCustomers();
            List<OdooInvoice> odooInvoices = odooInvoiceService.fetchOdooInvoices();
            odooInvoices = odooInvoices.stream().filter(invoice -> invoice.getCustomerName() != null && !invoice.getCustomerName().isEmpty()).collect(Collectors.toList());

            List<OdooCustomer> odooCustomers = odooCustomerService.fetchOdooCustomers();
            logger.info("Invoice synchronization completed successfully");
        } catch (Exception e) {
            logger.error("Error during invoice synchronization: {}", e.getMessage(), e);
            throw new MiddlewareException("Error during invoice synchronization", e);
        }
    }


}

