package com.zimworx.odoo_bill_integration.errorhandlers;

public class MiddlewareException extends RuntimeException {
    public MiddlewareException(String message) {
        super(message);
    }

    public MiddlewareException(String message, Throwable cause) {
        super(message, cause);
    }
}

