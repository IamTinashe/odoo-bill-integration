package com.zimworx.odoo_bill_integration.services.bill.implementations;

import com.zimworx.odoo_bill_integration.exceptions.BillServiceException;
import com.zimworx.odoo_bill_integration.services.bill.BillAuthenticationService;
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

import java.util.Map;

@Service
public class BillAuthenticationServiceImpl implements BillAuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(BillAuthenticationServiceImpl.class);

    private final RestTemplate restTemplate;
    private final String billApiUrl;
    private final String username;
    private final String password;
    private final String orgId;
    private final String devKey;

    private String sessionId;
    private long sessionExpiryTime;

    @Autowired
    public BillAuthenticationServiceImpl(
            RestTemplate restTemplate,
            @Value("${bill.api.url}") String billApiUrl,
            @Value("${bill.username}") String username,
            @Value("${bill.password}") String password,
            @Value("${bill.orgId}") String orgId,
            @Value("${bill.devKey}") String devKey) {
        this.restTemplate = restTemplate;
        this.billApiUrl = billApiUrl;
        this.username = username;
        this.password = password;
        this.orgId = orgId;
        this.devKey = devKey;
    }

    @Override
    public synchronized String getSessionId() {
        if (sessionId == null || System.currentTimeMillis() > sessionExpiryTime) {
            renewSession();
        }
        return sessionId;
    }

    @Override
    public synchronized void renewSession() {
        logger.info("Renewing Bill.com session");
        String url = billApiUrl + "/Login.json";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("userName", username);
        formData.add("password", password);
        formData.add("orgId", orgId);
        formData.add("devKey", devKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> responseData = (Map<String, Object>) response.getBody().get("response_data");
                this.sessionId = (String) responseData.get("sessionId");
                this.sessionExpiryTime = System.currentTimeMillis() + (115 * 60 * 1000);
                logger.info("Session renewed successfully. Session ID: {}", sessionId);
            } else {
                logger.error("Failed to authenticate with Bill.com: {}", response.getBody());
                throw new BillServiceException("Failed to authenticate with Bill.com");
            }
        } catch (Exception e) {
            logger.error("Exception during Bill.com authentication: {}", e.getMessage(), e);
            throw new BillServiceException("Exception during Bill.com authentication", e);
        }
    }
}
