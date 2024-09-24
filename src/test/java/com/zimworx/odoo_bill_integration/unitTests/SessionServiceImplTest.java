package com.zimworx.odoo_bill_integration.unitTests;

import com.zimworx.odoo_bill_integration.services.impl.BillAuthenticationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BillAuthenticationServiceImpl sessionService;

    @Test
    void testRenewSession() {
        sessionService.renewSession();

        assertNotNull(sessionService.getSessionId());
        verify(restTemplate, times(1)).postForEntity(anyString(), any(), any());
    }
}