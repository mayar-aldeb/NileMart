package org.route.nilemartspringboot.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.route.nilemartspringboot.dto.cart.CartItemResponse;
import org.route.nilemartspringboot.dto.cart.CartResponse;
import org.route.nilemartspringboot.dto.customer.CustomerResponse;
import org.route.nilemartspringboot.dto.product.ProductResponse;
import org.route.nilemartspringboot.model.Cart;
import org.route.nilemartspringboot.model.CartItem;
import org.route.nilemartspringboot.model.Customer;
import org.route.nilemartspringboot.model.Product;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-11-24T18:01:02+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.2 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public CartResponse toCartResponse(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartResponse.CartResponseBuilder cartResponse = CartResponse.builder();

        cartResponse.id( cart.getId() );
        cartResponse.customer( customerToCustomerResponse( cart.getCustomer() ) );
        cartResponse.cartItems( cartItemListToCartItemResponseList( cart.getCartItems() ) );

        return cartResponse.build();
    }

    protected CustomerResponse customerToCustomerResponse(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponse customerResponse = new CustomerResponse();

        customerResponse.setId( customer.getId() );
        customerResponse.setName( customer.getName() );
        customerResponse.setEmail( customer.getEmail() );
        customerResponse.setPhone( customer.getPhone() );

        return customerResponse;
    }

    protected ProductResponse productToProductResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.setId( product.getId() );
        productResponse.setName( product.getName() );
        productResponse.setCategory( product.getCategory() );
        productResponse.setPrice( product.getPrice() );

        return productResponse;
    }

    protected CartItemResponse cartItemToCartItemResponse(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemResponse.CartItemResponseBuilder cartItemResponse = CartItemResponse.builder();

        cartItemResponse.id( cartItem.getId() );
        cartItemResponse.product( productToProductResponse( cartItem.getProduct() ) );
        cartItemResponse.quantity( cartItem.getQuantity() );
        cartItemResponse.addedAt( cartItem.getAddedAt() );
        cartItemResponse.lineTotal( cartItem.getLineTotal() );

        return cartItemResponse.build();
    }

    protected List<CartItemResponse> cartItemListToCartItemResponseList(List<CartItem> list) {
        if ( list == null ) {
            return null;
        }

        List<CartItemResponse> list1 = new ArrayList<CartItemResponse>( list.size() );
        for ( CartItem cartItem : list ) {
            list1.add( cartItemToCartItemResponse( cartItem ) );
        }

        return list1;
    }
}
