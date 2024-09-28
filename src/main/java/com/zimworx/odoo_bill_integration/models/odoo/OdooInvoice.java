package com.zimworx.odoo_bill_integration.models.odoo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OdooInvoice {

    @NonNull
    Integer id;
    Integer amount_paid;
    Integer residual_amount;
    Integer amount_total;
}
