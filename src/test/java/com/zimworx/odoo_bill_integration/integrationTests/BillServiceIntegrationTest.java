package com.zimworx.odoo_bill_integration.integrationTests;


import com.zimworx.odoo_bill_integration.OdooBillIntegrationApplication;
import com.zimworx.odoo_bill_integration.services.BillService;
import com.zimworx.odoo_bill_integration.models.bill.invoiceResponse.Invoice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = OdooBillIntegrationApplication.class)
public class BillServiceIntegrationTest {

    @Autowired
    private BillService billService;

    @Test
    void testFetchInvoices() {
        // Simulate a successful response from Bill.com
        RestTemplate restTemplate = mock(RestTemplate.class);
        Invoice[] invoices = new Invoice[]{new Invoice("1", "Invoice1"), new Invoice("2", "Invoice2")};
        when(restTemplate.getForObject(anyString(), eq(Invoice[].class)))
                .thenReturn(invoices);

        // Use the mock RestTemplate in the BillService
        //BillServiceImpl billServiceImpl = new BillServiceImpl(restTemplate, mock(SessionService.class), "https://api-stage.bill.com/api/v2");
        //List<Invoice> fetchedInvoices = billServiceImpl.fetchInvoices();

        //assertNotNull(fetchedInvoices);
        //assertTrue(fetchedInvoices.size() > 0);
    }
}

