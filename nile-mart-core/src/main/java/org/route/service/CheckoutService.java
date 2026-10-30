package org.route.service;

import lombok.RequiredArgsConstructor;
import org.route.model.Customer;
import org.route.model.Governorate;
import org.route.model.Order;
import org.route.model.ShoppingCart;
import org.route.repository.OrderRegistry;
import org.route.service.notification.NotificationService;
import org.route.service.shipping.ShippingQuoteService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class CheckoutService {
    private final ProductService productService;
    private final ShippingQuoteService shippingQuoteService;
    private final TaxCalculator taxCalculator;
    private final OrderRegistry orderRegistry;
    private final NotificationService notificationService;
    private final CartService cartService;

    public Order createOrder(ShoppingCart cart,Governorate governorate,Customer customer) {
        BigDecimal subtotal = cartService.calculateSubtotal(cart).setScale(2, RoundingMode.HALF_UP);
        BigDecimal vat = taxCalculator.calculateTax(subtotal).setScale(2, RoundingMode.HALF_UP);
        BigDecimal shippingFee = shippingQuoteService.quote(governorate,subtotal).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total =  subtotal.add(vat).add(shippingFee).setScale(2, RoundingMode.HALF_UP);
        Order order =Order.builder().
                orderNumber(orderRegistry.createOrderId()).
                customer(customer).
                governorate(governorate).
                cartId(cart.getCartNumber()).
                subtotal(subtotal).
                vat(vat).
                shippingFee(shippingFee).
                total(total).
                build();
        orderRegistry.save(order);
        return order;
    }

    public void placeOrder(ShoppingCart cart, Governorate governorate, Customer customer) {
        cartService.isEmpty(cart);
        cart.getProducts().forEach(productService::reduceStock);
        Order order = createOrder(cart,governorate,customer);
        notificationService.sendNotifications(customer,": Order "+order.getOrderNumber()+" confirmed. Total "+ order.getTotal()+" EGP");
        cartService.clear(cart);
    }



}
