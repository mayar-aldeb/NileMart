package org.route.service.shipping;

import org.route.model.Governorate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("freeShipping")
public class FreeShippingCalculator implements ShippingCalculator {
    @Override
    public BigDecimal calculateShipping(Governorate governorate) {
        return BigDecimal.ZERO;
    }
}
