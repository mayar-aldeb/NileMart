package org.route.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ShoppingCart {

    private int cartNumber;
    private Customer customer;
    private Map<String,Integer> products;


}
