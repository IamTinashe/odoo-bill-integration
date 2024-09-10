package com.zimworx.odoo_bill_integration.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    private String entity;
    private String id;
    private String isActive;
    private String createdBy;
    private Date createdTime;
    private Date updatedTime;
    private String customerId;
    private String invoiceNumber;
    private String invoiceDate;
    private String dueDate;
    private String glPostingDate;
    private double amount;
    private Double localAmount;
    private Double exchangeRate;
    private double amountDue;
    private String paymentStatus;
    private String description;
    private String poNumber;
    private boolean isToBePrinted;
    private boolean isToBeEmailed;
    private Date lastSentTime;
    private String itemSalesTax;
    private double salesTaxPercentage;
    private double salesTaxTotal;
    private String terms;
    private String salesRep;
    private String FOB;
    private String shipDate;
    private String shipMethod;
    private String departmentId;
    private String locationId;
    private String actgClassId;
    private String jobId;
    private String payToBankAccountId;
    private String payToChartOfAccountId;
    private String invoiceTemplateId;
    private boolean hasAutoPay;
    private String source;
    private String emailDeliveryOption;
    private String mailDeliveryOption;
    private double creditAmount;
    private String quickbooksId;
    private String recInvoiceTemplateId;
    private String financingStatus;
    private String netBillId;
    private String netOrgId;
    private double scheduledAmount;
    private String stylingId;
    private String stylingRevision;
    private String financingModelStatus;
    private String financingErrorType;
    private List<InvoiceLineItem> invoiceLineItems;
    private List<Object> invoiceAdjustments; // Assuming adjustments are empty or unknown type
    private List<Object> invoiceCustomFields;

    public Invoice(String number, String invoice1) {
    }
}
