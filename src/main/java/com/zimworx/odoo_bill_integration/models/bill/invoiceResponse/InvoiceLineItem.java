package com.zimworx.odoo_bill_integration.models.bill.invoiceResponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceLineItem {
    private String entity;
    @NonNull
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date updatedTime;
    private String invoiceId;
    private String itemId;
    private int quantity;
    private double amount;
    private double price;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date serviceDate;
    private Double ratePercent;
    private String chartOfAccountId;
    private String departmentId;
    private String locationId;
    private String actgClassId;
    private String jobId;
    private String description;
    private boolean taxable;
    private String taxCode;
    private int lineOrder;
}
