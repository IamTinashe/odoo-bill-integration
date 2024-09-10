package com.zimworx.odoo_bill_integration.services.impl;

import com.zimworx.odoo_bill_integration.models.Invoice;
import com.zimworx.odoo_bill_integration.services.BillService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final RestTemplate restTemplate;
    private final String billApiUrl;

    public BillServiceImpl(RestTemplate restTemplate, @Value("${bill.api.url}") String billApiUrl) {
        this.restTemplate = restTemplate;
        this.billApiUrl = billApiUrl;
    }

    @Override
    public List<Invoice> fetchInvoices() {
        String url = UriComponentsBuilder.fromHttpUrl(billApiUrl)
                .path("/invoices")
                .toUriString();

        Invoice[] invoices = restTemplate.getForObject(url, Invoice[].class);
        return Arrays.asList(invoices);
    }
}
