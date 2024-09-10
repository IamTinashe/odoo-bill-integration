package com.zimworx.odoo_bill_integration.services.impl;

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

import java.util.Map;

@Service
public class SessionServiceImpl implements SessionService {

    private final RestTemplate restTemplate;
    private final String billApiUrl;
    private final String username;
    private final String password;
    private final String orgId;
    private final String devKey;

    private String sessionId;
    private long sessionExpiryTime;

    public SessionServiceImpl(
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
    public String getSessionId() {
        if (sessionId == null || System.currentTimeMillis() > sessionExpiryTime) {
            renewSession();
        }
        return sessionId;
    }

    @Override
    public void renewSession() {
        String url = billApiUrl + "/Login.json";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("userName", username);
        formData.add("password", password);
        formData.add("orgId", orgId);
        formData.add("devKey", devKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseData = (Map<String, Object>) response.getBody().get("response_data");
            this.sessionId = (String) responseData.get("sessionId");
            // Set session expiry to 1 hour 55 minutes to allow for buffer before renewal
            this.sessionExpiryTime = System.currentTimeMillis() + (115 * 60 * 1000);
        } else {
            throw new RuntimeException("Failed to authenticate with Bill.com: " + response.getBody());
        }
    }
}
