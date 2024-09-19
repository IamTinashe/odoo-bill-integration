package com.zimworx.odoo_bill_integration.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OdooProperties {
    @Value("${odoo.api.url}")
    private String url;

    @Value("${odoo.api.db}")
    private String db;

    @Value("${odoo.api.username}")
    private String username;

    @Value("${odoo.api.password}")
    private String password;
}
