package org.route.service.shipping;

import org.route.model.Governorate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface ShippingCalculator {
    BigDecimal calculateShipping(Governorate governorate);
}
