package org.route.repository;

import org.route.exception.StoreException;
import org.route.model.Customer;
import org.route.model.ShoppingCart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CartRepository {
    private final Map<Integer, ShoppingCart> carts= new HashMap<>();
    private static int cartCount=0;

    public void save(ShoppingCart cart) {
        if(carts.containsKey(cart.getCartNumber()))
            throw new StoreException("cartNumber already exists");
        carts.put(cart.getCartNumber(),cart);
    }

    public int createCart(Customer customer) {
        save(new ShoppingCart(++cartCount,customer,new HashMap<>()));
        return cartCount;

    }

    public void removeCart(ShoppingCart cart) {
        carts.remove(cart.getCartNumber());
    }

    public Optional<ShoppingCart> findById(int id) {
        return Optional.ofNullable(carts.get(id));
    }



}
