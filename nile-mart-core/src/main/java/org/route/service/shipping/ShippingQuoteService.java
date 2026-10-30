package org.route.service.shipping;

import org.route.model.Governorate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Service
public class ShippingQuoteService {

    private final ShippingCalculator primaryCalculator;
    private final ShippingCalculator freeShippingCalculator;
    private final BigDecimal freeShippingThreshold;

    public ShippingQuoteService(ShippingCalculator primaryCalculator
            , @Qualifier("freeShipping") ShippingCalculator freeShippingCalculator
            , @Value("${store.free-shipping-threshold}") BigDecimal freeShippingThreshold) {
        this.primaryCalculator = primaryCalculator;
        this.freeShippingCalculator = freeShippingCalculator;
        this.freeShippingThreshold = freeShippingThreshold;
    }

    public BigDecimal quote(Governorate governorate, BigDecimal subtotal)
    {
        if(subtotal.compareTo(freeShippingThreshold) >= 0 && governorate.shippingZone().isFreeShip()){
            return freeShippingCalculator.calculateShipping(governorate);
        }
        return primaryCalculator.calculateShipping(governorate);
    }
}
