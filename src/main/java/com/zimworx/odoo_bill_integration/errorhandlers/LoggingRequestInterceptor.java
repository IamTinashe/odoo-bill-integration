package com.zimworx.odoo_bill_integration.errorhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        logRequestDetails(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponseDetails(response);

        return response;
    }

    private void logRequestDetails(HttpRequest request, byte[] body) throws UnsupportedEncodingException {
        logger.debug("Request URI: {}", request.getURI());
        logger.debug("Request Method: {}", request.getMethod());
        logger.debug("Request Headers: {}", request.getHeaders());
        logger.debug("Request Body: {}", new String(body, "UTF-8"));
    }

    private void logResponseDetails(ClientHttpResponse response) throws IOException {
        logger.debug("Response Status Code: {}", response.getStatusCode());
        logger.debug("Response Status Text: {}", response.getStatusText());
        // You can also log response headers and body if needed
    }
}

