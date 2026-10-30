package org.route.service.pricing;

import org.route.model.Product;

import java.math.BigDecimal;

public interface PricingService {

    BigDecimal getPrice(Product product, int qty);
}
