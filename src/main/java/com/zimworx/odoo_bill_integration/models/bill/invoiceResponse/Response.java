package com.zimworx.odoo_bill_integration.models.bill.invoiceResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private int responseStatus;
    private String responseMessage;
    private List<Invoice> responseData;
}
