package com.zimworx.odoo_bill_integration.services.impl;

import com.zimworx.odoo_bill_integration.models.Invoice;
import com.zimworx.odoo_bill_integration.services.OdooService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OdooServiceImpl implements OdooService {

    private final RestTemplate restTemplate;
    private final String odooApiUrl;

    public OdooServiceImpl(RestTemplate restTemplate, @Value("${odoo.api.url}") String odooApiUrl) {
        this.restTemplate = restTemplate;
        this.odooApiUrl = odooApiUrl;
    }

    @Override
    public void postInvoice(Invoice invoice) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Invoice> request = new HttpEntity<>(invoice, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                odooApiUrl + "/invoices",
                HttpMethod.POST,
                request,
                String.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to post invoice to Odoo");
        }
    }
}