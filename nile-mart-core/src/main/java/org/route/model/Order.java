package org.route.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Order {
    private String orderNumber;
    private Customer customer;
    private Governorate governorate;
    private int cartId;
    private BigDecimal subtotal;
    private BigDecimal vat;
    private BigDecimal shippingFee;
    private BigDecimal total;


}
