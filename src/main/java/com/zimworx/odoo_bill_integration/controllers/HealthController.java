package com.zimworx.odoo_bill_integration.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
public class HealthController {

    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    @GetMapping
    public ResponseEntity<String> health() {
        logger.info("Health check endpoint accessed");
        return ResponseEntity.ok("Application is running");
    }
}

