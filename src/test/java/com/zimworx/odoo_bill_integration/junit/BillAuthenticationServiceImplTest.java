package com.zimworx.odoo_bill_integration.junit;

import com.zimworx.odoo_bill_integration.errorhandlers.BillServiceException;
import com.zimworx.odoo_bill_integration.services.bill.implementations.BillAuthenticationServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BillAuthenticationServiceImplTest {

    @InjectMocks
    private BillAuthenticationServiceImpl billAuthenticationService;

    @Mock
    private RestTemplate restTemplate;

    @Value("${bill.api.url}")
    private String billApiUrl;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(billAuthenticationService, "billApiUrl", billApiUrl);
        // Set other fields...
    }

    @Test
    public void testGetSessionId_Success() {
        // Arrange
        Map<String, Object> responseData = new HashMap<>();
        Map<String, Object> responseMap = new HashMap<>();
        responseData.put("sessionId", "dummySessionId");
        responseMap.put("response_data", responseData);

        ResponseEntity<Map> responseEntity = new ResponseEntity<>(responseMap, HttpStatus.OK);

        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(Map.class)))
                .thenReturn(responseEntity);

        // Act
        String sessionId = billAuthenticationService.getSessionId();

        // Assert
        assertNotNull(sessionId);
        assertEquals("dummySessionId", sessionId);
    }

    @Test(expected = BillServiceException.class)
    public void testGetSessionId_Failure() {
        // Arrange
        ResponseEntity<Map> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(Map.class)))
                .thenReturn(responseEntity);

        // Act
        billAuthenticationService.getSessionId();
    }
}

