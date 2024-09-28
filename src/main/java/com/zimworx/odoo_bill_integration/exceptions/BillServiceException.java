package com.zimworx.odoo_bill_integration.exceptions;

public class BillServiceException extends RuntimeException {
    public BillServiceException(String message) {
        super(message);
    }

    public BillServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

