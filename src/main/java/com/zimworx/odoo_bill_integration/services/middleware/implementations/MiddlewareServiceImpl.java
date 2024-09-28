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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MiddlewareServiceImpl implements MiddlewareService {

    private static final Logger logger = LoggerFactory.getLogger(MiddlewareServiceImpl.class);

    private final InvoiceService invoiceService;
    private final CustomerService customerService;
    private final OdooInvoiceService odooInvoiceService;

    @Autowired
    public MiddlewareServiceImpl(
            InvoiceService invoiceService,
            CustomerService customerService,
            OdooInvoiceService odooInvoiceService) {
        this.invoiceService = invoiceService;
        this.customerService = customerService;
        this.odooInvoiceService = odooInvoiceService;
    }

    @Override
    public void syncInvoices() {
        logger.info("Starting invoice synchronization");
        try {
            // Fetch data from Bill.com
            List<Invoice> billInvoices = invoiceService.fetchBillInvoices();
            List<Customer> billCustomers = customerService.fetchBillCustomers();

            // Fetch invoices from Odoo
            List<OdooInvoice> odooInvoices = odooInvoiceService.fetchOdooInvoices();

            // Remove Odoo invoices where customerName is empty
            odooInvoices = odooInvoices.stream()
                    .filter(invoice -> invoice.getCustomerName() != null && !invoice.getCustomerName().isEmpty())
                    .collect(Collectors.toList());

            // Map customer IDs to names
            Map<String, String> customerIdToName = billCustomers.stream()
                    .collect(Collectors.toMap(Customer::getId, Customer::getCompanyName));

            // Associate customer names with Bill.com invoices
            for (Invoice invoice : billInvoices) {
                String customerId = invoice.getCustomerId();
                String customerName = customerIdToName.get(customerId);
                if (customerName != null && !customerName.isEmpty()) {
                    invoice.setCustomerName(customerName);
                } else {
                    logger.warn("Customer ID {} not found or has empty name.", customerId);
                    invoice.setCustomerName("");
                }
            }

            // Create sets of customer names
            Set<String> billInvoiceCustomerNames = billInvoices.stream()
                    .map(Invoice::getCustomerName)
                    .filter(name -> name != null && !name.isEmpty())
                    .collect(Collectors.toSet());

            Set<String> odooInvoiceCustomerNames = odooInvoices.stream()
                    .map(OdooInvoice::getCustomerName)
                    .filter(name -> name != null && !name.isEmpty())
                    .collect(Collectors.toSet());

            // Compare and print results
            compareInvoices(billInvoiceCustomerNames, odooInvoiceCustomerNames);

            logger.info("Invoice synchronization completed successfully");
        } catch (Exception e) {
            logger.error("Error during invoice synchronization: {}", e.getMessage(), e);
            throw new MiddlewareException("Error during invoice synchronization", e);
        }
    }

    private void compareInvoices(Set<String> billInvoiceCustomerNames, Set<String> odooInvoiceCustomerNames) {
        // Invoices that exist in both systems
        Set<String> invoicesInBoth = new HashSet<>(billInvoiceCustomerNames);
        invoicesInBoth.retainAll(odooInvoiceCustomerNames);

        // Invoices that exist only in Bill.com
        Set<String> invoicesOnlyInBill = new HashSet<>(billInvoiceCustomerNames);
        invoicesOnlyInBill.removeAll(odooInvoiceCustomerNames);

        // Invoices that exist only in Odoo
        Set<String> invoicesOnlyInOdoo = new HashSet<>(odooInvoiceCustomerNames);
        invoicesOnlyInOdoo.removeAll(billInvoiceCustomerNames);

        // Print results
        for (String customerName : invoicesInBoth) {
            logger.info("Invoice for customer '{}' exists in both Bill.com and Odoo.", customerName);
        }

        for (String customerName : invoicesOnlyInBill) {
            logger.info("Invoice for customer '{}' exists in Bill.com but not in Odoo.", customerName);
        }

        for (String customerName : invoicesOnlyInOdoo) {
            logger.info("Invoice for customer '{}' exists in Odoo but not in Bill.com.", customerName);
        }
    }
}
