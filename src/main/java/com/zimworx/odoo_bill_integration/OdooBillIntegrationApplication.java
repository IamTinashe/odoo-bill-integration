package com.zimworx.odoo_bill_integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.zimworx.odoo_bill_integration"})
public class OdooBillIntegrationApplication {
	private static final Logger logger = LoggerFactory.getLogger(OdooBillIntegrationApplication.class);

	public static void main(String[] args) {
		logger.info("Starting application...");

		SpringApplication.run(OdooBillIntegrationApplication.class, args);
	}

}
