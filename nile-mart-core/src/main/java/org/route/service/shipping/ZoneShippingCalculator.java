package org.route.service.shipping;

import org.route.model.Governorate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@Primary
public class ZoneShippingCalculator implements ShippingCalculator {

    @Override
    public BigDecimal calculateShipping(Governorate governorate) {
        return governorate.shippingZone().getFee();
    }

}
