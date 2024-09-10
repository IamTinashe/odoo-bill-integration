package com.zimworx.odoo_bill_integration.services.impl;

import com.zimworx.odoo_bill_integration.models.Invoice;
import com.zimworx.odoo_bill_integration.models.Response;
import com.zimworx.odoo_bill_integration.services.BillService;
import com.zimworx.odoo_bill_integration.services.SessionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class BillServiceImpl implements BillService {

    private final RestTemplate restTemplate;
    private final SessionService sessionService;
    private final String billApiUrl;
    private final String devKey;

    private final String data;

    private List<Invoice> invoices;

    public BillServiceImpl(RestTemplate restTemplate, SessionService sessionService, @Value("${bill.api.url}") String billApiUrl, @Value("${bill.devKey}") String devKey, @Value("${bill.data}") String data) {
        this.restTemplate = restTemplate;
        this.sessionService = sessionService;
        this.billApiUrl = billApiUrl;
        this.devKey = devKey;
        this.data = data;
    }

    @Override
    public List<Invoice> fetchInvoices() {
        String sessionId = sessionService.getSessionId();

        String url = billApiUrl + "/List/Invoice.json";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("sessionId", sessionId);
        formData.add("data", data);
        formData.add("devKey", devKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);


        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseData = (Map<String, Object>) response.getBody();
            invoices = (List<Invoice>) responseData.get("response_data");
        } else {
            throw new RuntimeException("Could not fetch invoices: " + response.getBody());
        }

        return invoices;
    }
}