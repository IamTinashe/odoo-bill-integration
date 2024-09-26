package com.zimworx.odoo_bill_integration.config;

import com.zimworx.odoo_bill_integration.errorhandlers.LoggingRequestInterceptor;
import com.zimworx.odoo_bill_integration.errorhandlers.RestTemplateResponseErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // Set a custom error handler
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

        restTemplate.getInterceptors().add(new LoggingRequestInterceptor());


        return restTemplate;
    }
}


