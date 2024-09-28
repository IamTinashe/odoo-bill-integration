package com.zimworx.odoo_bill_integration.services.bill.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zimworx.odoo_bill_integration.errorhandlers.BillServiceException;
import com.zimworx.odoo_bill_integration.models.bill.invoiceResponse.Invoice;
import com.zimworx.odoo_bill_integration.services.bill.BillAuthenticationService;
import com.zimworx.odoo_bill_integration.services.bill.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    private final RestTemplate restTemplate;
    private final BillAuthenticationService billAuthenticationService;
    private final String billApiUrl;
    private final String devKey;
    private final String data;

    @Autowired
    public InvoiceServiceImpl(
            RestTemplate restTemplate,
            BillAuthenticationService billAuthenticationService,
            @Value("${bill.api.url}") String billApiUrl,
            @Value("${bill.devKey}") String devKey,
            @Value("${bill.data}") String data) {
        this.restTemplate = restTemplate;
        this.billAuthenticationService = billAuthenticationService;
        this.billApiUrl = billApiUrl;
        this.devKey = devKey;
        this.data = data;
    }

    @Override
    public List<Invoice> fetchBillInvoices() {
        logger.info("Fetching invoices from Bill.com");
        String sessionId = billAuthenticationService.getSessionId();
        String url = billApiUrl + "/List/Invoice.json";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("sessionId", sessionId);
        formData.add("data", data);
        formData.add("devKey", devKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> responseData = response.getBody();
                List<Map<String, Object>> rawInvoices = (List<Map<String, Object>>) responseData.get("response_data");

                ObjectMapper objectMapper = new ObjectMapper();
                List<Invoice> invoices = rawInvoices.stream()
                        .map(rawInvoice -> objectMapper.convertValue(rawInvoice, Invoice.class))
                        .collect(Collectors.toList());

                logger.info("Fetched {} invoices", invoices.size());
                return invoices;
            } else {
                logger.error("Failed to fetch invoices: {}", response.getBody());
                throw new BillServiceException("Could not fetch invoices");
            }
        } catch (Exception e) {
            logger.error("Exception while fetching invoices: {}", e.getMessage(), e);
            throw new BillServiceException("Exception while fetching invoices", e);
        }
    }
}

