package com.zimworx.odoo_bill_integration.errorhandlers;

public class OdooServiceException extends RuntimeException {
    public OdooServiceException(String message) {
        super(message);
    }

    public OdooServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

