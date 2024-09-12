package com.zimworx.odoo_bill_integration.controllers;

import com.zimworx.odoo_bill_integration.services.MiddlewareService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/middleware")
public class MiddlewareController {

    private final MiddlewareService middlewareService;

    public MiddlewareController(MiddlewareService middlewareService) {
        this.middlewareService = middlewareService;
    }

    @PostMapping("/sync")
    public void syncInvoices() {
        middlewareService.syncInvoices();
    }
}