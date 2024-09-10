package com.zimworx.odoo_bill_integration.services.impl;

import com.zimworx.odoo_bill_integration.models.Invoice;
import com.zimworx.odoo_bill_integration.services.BillService;
import com.zimworx.odoo_bill_integration.services.SessionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final RestTemplate restTemplate;
    private final SessionService sessionService;
    private final String billApiUrl;

    public BillServiceImpl(RestTemplate restTemplate, SessionService sessionService, @Value("${bill.api.url}") String billApiUrl) {
        this.restTemplate = restTemplate;
        this.sessionService = sessionService;
        this.billApiUrl = billApiUrl;
    }

    @Override
    public List<Invoice> fetchInvoices() {
        String sessionId = sessionService.getSessionId();

        String url = UriComponentsBuilder.fromHttpUrl(billApiUrl)
                .path("/invoices")
                .queryParam("sessionId", sessionId)
                .toUriString();

        Invoice[] invoices = restTemplate.getForObject(url, Invoice[].class);
        return Arrays.asList(invoices);
    }
}