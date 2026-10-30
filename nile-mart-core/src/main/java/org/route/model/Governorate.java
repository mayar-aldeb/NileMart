package org.route.model;

import org.route.enums.ShippingZone;

public record Governorate(String governorate, ShippingZone shippingZone) {

    public Governorate(String governorate, ShippingZone shippingZone) {
        this.governorate = governorate.toLowerCase();
        this.shippingZone = shippingZone;
    }

}
