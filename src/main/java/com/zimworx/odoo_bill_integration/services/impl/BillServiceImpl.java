package com.zimworx.odoo_bill_integration.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zimworx.odoo_bill_integration.models.customerResponse.Customer;
import com.zimworx.odoo_bill_integration.models.invoiceResponse.Invoice;
import com.zimworx.odoo_bill_integration.services.BillService;
import com.zimworx.odoo_bill_integration.services.BillSessionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    private final RestTemplate restTemplate;
    private BillSessionService billSessionService;
    private final String billApiUrl;
    private final String devKey;

    private final String data;

    private List<Invoice> invoices;

    public BillServiceImpl(RestTemplate restTemplate, BillSessionService billSessionService, @Value("${bill.api.url}") String billApiUrl, @Value("${bill.devKey}") String devKey, @Value("${bill.data}") String data) {
        this.restTemplate = restTemplate;
        this.billSessionService = billSessionService;
        this.billApiUrl = billApiUrl;
        this.devKey = devKey;
        this.data = data;
    }

    @Override
    public List<Invoice> fetchInvoices() {
        String sessionId = billSessionService.getSessionId();
        String url = billApiUrl + "/List/Invoice.json";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("sessionId", sessionId);
        formData.add("data", data);
        formData.add("devKey", devKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        List<Invoice> invoices = new ArrayList<>();

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseData = (Map<String, Object>) response.getBody();
            List<Map<String, Object>> rawInvoices = (List<Map<String, Object>>) responseData.get("response_data");

            ObjectMapper objectMapper = new ObjectMapper();  // Jackson ObjectMapper to convert Maps to Invoice objects
            invoices = rawInvoices.stream()
                    .map(rawInvoice -> objectMapper.convertValue(rawInvoice, Invoice.class))
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("Could not fetch invoices: " + response.getBody());
        }

        return invoices;
    }

    @Override
    public List<Customer> fetchCustomers(){
        String sessionId = billSessionService.getSessionId();
        String url = billApiUrl + "/List/Customer.json";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("sessionId", sessionId);
        formData.add("data", data);
        formData.add("devKey", devKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        List<Customer> customers = new ArrayList<>();

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseData = (Map<String, Object>) response.getBody();
            List<Map<String, Object>> rawCustomers = (List<Map<String, Object>>) responseData.get("response_data");

            ObjectMapper objectMapper = new ObjectMapper();  // Jackson ObjectMapper to convert Maps to Invoice objects
            customers = rawCustomers.stream()
                    .map(rawCustomer -> objectMapper.convertValue(rawCustomer, Customer.class))
                    .collect(Collectors.toList());

        } else {
            throw new RuntimeException("Could not fetch customers: " + response.getBody());
        }

        return customers;
    }
}