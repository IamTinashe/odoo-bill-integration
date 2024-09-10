package com.zimworx.odoo_bill_integration.integrationTests;

import com.zimworx.odoo_bill_integration.OdooBillIntegrationApplication;
import com.zimworx.odoo_bill_integration.services.SessionService;
import com.zimworx.odoo_bill_integration.services.impl.SessionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = OdooBillIntegrationApplication.class)
public class SessionServiceIntegrationTest {

    @Autowired
    private SessionService sessionService;

    @Test
    void testGetSessionId() {
        // Simulate a successful response from Bill.com
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.postForEntity(anyString(), any(), eq(Map.class)))
                .thenReturn(new ResponseEntity<>(Map.of(
                        "response_status", 0,
                        "response_message", "Success",
                        "response_data", Map.of(
                                "sessionId", "test-session-id",
                                "apiEndPoint", "https://api-stage.bill.com/api/v2"
                        )
                ), HttpStatus.OK));

        // Use the mock RestTemplate in the SessionService
        SessionServiceImpl sessionServiceImpl = new SessionServiceImpl(restTemplate, "https://api-stage.bill.com/api/v2", "username", "password", "orgId", "devKey");
        sessionServiceImpl.renewSession();
        String sessionId = sessionServiceImpl.getSessionId();

        assertNotNull(sessionId);
        assertEquals("test-session-id", sessionId);
    }
}

