package com.zimworx.odoo_bill_integration;

import com.zimworx.odoo_bill_integration.services.impl.SessionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SessionServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private SessionServiceImpl sessionService;

    @Test
    void testRenewSession() {
        // Setup mock response
        // ...

        sessionService.renewSession();

        assertNotNull(sessionService.getSessionId());
        verify(restTemplate, times(1)).postForEntity(anyString(), any(), any());
    }
}