package com.zimworx.odoo_bill_integration.errorhandlers;

public class BillServiceException extends RuntimeException {
    public BillServiceException(String message) {
        super(message);
    }

    public BillServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

