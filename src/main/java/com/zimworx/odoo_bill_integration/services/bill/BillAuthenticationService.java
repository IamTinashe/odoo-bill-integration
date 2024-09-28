package com.zimworx.odoo_bill_integration.services.bill;

public interface BillAuthenticationService {
    String getSessionId();
    void renewSession();
}
