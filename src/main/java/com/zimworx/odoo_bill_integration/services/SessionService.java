package com.zimworx.odoo_bill_integration.services;

public interface SessionService {
    String getSessionId();
    void renewSession();
}
