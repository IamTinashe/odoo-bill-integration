package com.zimworx.odoo_bill_integration.controllers;


import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping("")
    public Boolean health() {
        return true;
    }
}
