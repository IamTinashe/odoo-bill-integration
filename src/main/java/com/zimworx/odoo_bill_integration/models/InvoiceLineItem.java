package com.zimworx.odoo_bill_integration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceLineItem {
    private String entity;
    private String id;
    private Date createdTime;
    private Date updatedTime;
    private String invoiceId;
    private String itemId;
    private int quantity;
    private double amount;
    private double price;
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
