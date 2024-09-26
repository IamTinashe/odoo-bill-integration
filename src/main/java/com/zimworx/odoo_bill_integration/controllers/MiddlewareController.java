package com.zimworx.odoo_bill_integration.controllers;



import com.zimworx.odoo_bill_integration.services.middleware.MiddlewareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/api/v1/middleware")
public class MiddlewareController {

    private static final Logger logger = LoggerFactory.getLogger(MiddlewareController.class);

    private final MiddlewareService middlewareService;

    @Autowired
    public MiddlewareController(MiddlewareService middlewareService) {
        this.middlewareService = middlewareService;
    }

    @PostMapping("/sync")
    public ResponseEntity<String> syncInvoices() {
        logger.info("Sync invoices request received");
        try {
            middlewareService.syncInvoices();
            return ResponseEntity.ok("Invoices synchronized successfully");
        } catch (Exception e) {
            logger.error("Error during invoice synchronization: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error during invoice synchronization");
        }
    }
}
