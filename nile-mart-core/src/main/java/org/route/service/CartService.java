package org.route.service;

import org.route.exception.StoreException;
import org.route.model.ShoppingCart;
import org.route.repository.CartRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final int maxQuantityPerProduct;

    public CartService(CartRepository cartRepository, ProductService productService, @Value("${store.max-quantity-per-product}") int maxQuantityPerProduct) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.maxQuantityPerProduct = maxQuantityPerProduct;
    }

    public ShoppingCart findById(int cartId){
       return cartRepository.findById(cartId).orElseThrow(()->new StoreException("cart not found"));
    }

    public void addItem(String sku, int qty, int cartId){
       ShoppingCart cart= cartRepository.findById(cartId).orElseThrow(() -> new StoreException("cart not found."));
       Integer oldQuantity= cart.getProducts().get(sku);
       if(oldQuantity!=null)
           qty=qty+oldQuantity;
        if(qty>maxQuantityPerProduct)
            throw new StoreException("requested quantity is too large");
        productService.checkQuantity(sku, qty);
        cart.getProducts().put(sku,qty);

    }

    public BigDecimal calculateSubtotal(ShoppingCart cart){
        BigDecimal subtotal = BigDecimal.ZERO;
        for(var cartItem:cart.getProducts().entrySet())
            subtotal=subtotal.add(productService.getPrice(cartItem.getKey(),cartItem.getValue()));
        return subtotal;
    }

    public void isEmpty(ShoppingCart cart){
        if(cart.getProducts().isEmpty())
            throw new StoreException("cart is empty");
    }

    public void clear(ShoppingCart cart){
        cart.getProducts().clear();
        cartRepository.removeCart(cart);
    }

}
