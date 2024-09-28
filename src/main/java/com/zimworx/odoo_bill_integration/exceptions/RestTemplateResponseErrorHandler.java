package com.zimworx.odoo_bill_integration.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        HttpStatusCode statusCode = httpResponse.getStatusCode();
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        logger.error("Response error: {} {}", httpResponse.getStatusCode(), httpResponse.getStatusText());
        // Handle the error accordingly
    }
}
