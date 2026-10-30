package org.route.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum ShippingZone {
    GREATER_CAIRO(BigDecimal.valueOf(50),true),
    ALEX_DELTA(BigDecimal.valueOf(65),true),
    CANAL(BigDecimal.valueOf(70),true),
    UPPER_EGYPT(BigDecimal.valueOf(85),true),
    REMOTE(BigDecimal.valueOf(120),false);

    private final BigDecimal fee;
    private final boolean freeShip;


}
