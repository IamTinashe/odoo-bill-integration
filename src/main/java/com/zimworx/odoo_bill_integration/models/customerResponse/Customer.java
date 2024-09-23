package com.zimworx.odoo_bill_integration.models.customerResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    private String entity;
    private String id;
    private String isActive;
    private Date createdTime;
    private Date updatedTime;
    private String name;
    private Object shortName;
    private String parentCustomerId;
    private String companyName;
    private String contactFirstName;
    private String contactLastName;
    private Object accNumber;
    private String billAddress1;
    private Object billAddress2;
    private Object billAddress3;
    private Object billAddress4;
    private String billAddressCity;
    private String billAddressState;
    private String billAddressCountry;
    private String billAddressZip;
    private String shipAddress1;
    private Object shipAddress2;
    private Object shipAddress3;
    private Object shipAddress4;
    private String shipAddressCity;
    private String shipAddressState;
    private String shipAddressCountry;
    private String shipAddressZip;
    private String email;
    private String phone;
    private Object altPhone;
    private Object fax;
    private Object description;
    private Object invoiceCurrency;
    private Object printAs;
    private String mergedIntoId;
    private Boolean hasAuthorizedToCharge;
    private String accountType;
    private String paymentTermId;
    private Integer balance;
    private Integer availCredit;
    private Object taxId;
    private Boolean hasBankAccount;
    private Boolean hasNetBankAccount;
    private Boolean hasBankAccountAutoPay;
    private Boolean hasCreditCard;
    private Boolean hasCreditCardAutoPay;
    private Object defaultDeliveryMethod;
    private Boolean isAutoChargeDismissed;
    private Boolean isTaxLiable;
    private String creationSource;
    private Boolean enabledInvoiceFinancing;
    private Boolean hasCustomerCard;
    private Boolean enabledAutoInvoiceFinancing;
}
