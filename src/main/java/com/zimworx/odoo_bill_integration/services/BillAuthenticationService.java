package com.zimworx.odoo_bill_integration.services;

public interface BillAuthenticationService {
    String getSessionId();
    void renewSession();
}
