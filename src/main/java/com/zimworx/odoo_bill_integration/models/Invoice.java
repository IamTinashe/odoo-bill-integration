package com.zimworx.odoo_bill_integration.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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
    private Double amount;
    private Double localAmount;
    private Double exchangeRate;
    private Double amountDue;
    private String paymentStatus;
    private String description;
    private String poNumber;
    private Boolean isToBePrinted;
    private Boolean isToBeEmailed;
    private Date lastSentTime;
    private String itemSalesTax;
    private Double salesTaxPercentage;
    private Double salesTaxTotal;
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
    private Boolean hasAutoPay;
    private String source;
    private String emailDeliveryOption;
    private String mailDeliveryOption;
    private Double creditAmount;
    private String quickbooksId;
    private String recInvoiceTemplateId;
    private String financingStatus;
    private String netBillId;
    private String netOrgId;
    private Double scheduledAmount;
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
