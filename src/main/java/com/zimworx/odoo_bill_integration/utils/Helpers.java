package com.zimworx.odoo_bill_integration.utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Helpers {

    public String getStringValue(Object value) {
        if (value instanceof Boolean && !((Boolean) value)) {
            return "";
        } else if (value != null) {
            return value.toString();
        } else {
            return "";
        }
    }
}
