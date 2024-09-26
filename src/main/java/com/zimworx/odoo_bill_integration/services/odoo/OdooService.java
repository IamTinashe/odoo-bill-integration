package com.zimworx.odoo_bill_integration.services.odoo;



import com.zimworx.odoo_bill_integration.models.odoo.customerResponse.Customer;
import com.zimworx.odoo_bill_integration.models.odoo.invoiceResponse.Invoice;

import java.util.List;

public interface OdooService {
    void postInvoice(Invoice invoice);
    void syncCustomers(List<Customer> customers);
    // Other methods...
}

