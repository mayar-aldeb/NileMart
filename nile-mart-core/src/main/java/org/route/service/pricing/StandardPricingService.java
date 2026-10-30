package org.route.service.pricing;

import org.route.model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Primary
public class StandardPricingService implements PricingService {

    @Override
    public BigDecimal getPrice(Product product, int qty) {
        return product.getPrice().multiply(BigDecimal.valueOf(qty));
    }
}
