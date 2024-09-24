package com.zimworx.odoo_bill_integration.services;

public interface BillSessionService {
    String getSessionId();
    void renewSession();
}
