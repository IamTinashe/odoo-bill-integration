package com.zimworx.odoo_bill_integration.config;

import com.zimworx.odoo_bill_integration.OdooBillIntegrationApplication;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "odoo.api")
public class OdooProperties {
    private String url;
    private String db;
    private String username;
    private String password;
    private Logger logger = LoggerFactory.getLogger(OdooProperties.class);

    @PostConstruct
    public void logProperties() {
        logger.info("OdooProperties loaded with URL: {} and DB: {}", url, db);
    }
}
