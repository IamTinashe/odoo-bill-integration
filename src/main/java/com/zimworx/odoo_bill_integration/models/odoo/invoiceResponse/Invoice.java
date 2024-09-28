package com.zimworx.odoo_bill_integration.models.odoo.invoiceResponse;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Invoice {

    @NonNull
    Integer id;
    Integer amount_paid;
    Integer residual_amount;
    Integer amount_total;
}
