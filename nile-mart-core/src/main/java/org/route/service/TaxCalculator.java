package org.route.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TaxCalculator {
    private final BigDecimal vat;

    public TaxCalculator(@Value("${store.vat-rate}") BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal calculateTax(BigDecimal subtotal) {
        return subtotal.multiply(vat);
    }
}
