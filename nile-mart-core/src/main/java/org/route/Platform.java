package org.route;

import lombok.RequiredArgsConstructor;
import org.route.exception.StoreException;
import org.route.model.Customer;
import org.route.model.Governorate;
import org.route.model.ShoppingCart;
import org.route.repository.CartRepository;
import org.route.repository.ZoneRepository;
import org.route.service.CartService;
import org.route.service.CheckoutService;
import org.route.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Platform {
    private final ProductService productService;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final CheckoutService checkoutService;
    private final ZoneRepository zoneRepository;

    public void addProductToCart(){
        productService.findAll();
        String sku = InputValidation.setValidString("Enter SKU: ");
        int quantity=InputValidation.setInt("Enter Quantity: ");
        int cartId =InputValidation.setInt("Enter Cart ID: ");
        cartService.addItem(sku,quantity,cartId);
        System.out.println("product added to cart.");
    }

    public void showCart(){
        int cartId =InputValidation.setInt("Enter Cart ID: ");
        ShoppingCart cart=cartService.findById(cartId);
        System.out.println("Cart ID: "+cartId);
        System.out.println("Customer: "+cart.getCustomer().fullName());
        cart.getProducts().forEach((sku, quantity) -> System.out.println("SKU: "+sku+", Quantity: "+quantity) );
    }

    public void checkout(Customer customer){
        int cartId =InputValidation.setInt("Enter Cart ID: ");
        ShoppingCart cart=cartService.findById(cartId);
        String governorate=InputValidation.setValidString("Enter Governorate: ");
        Governorate zone=zoneRepository.findGovernorate(governorate).orElseThrow(()->new StoreException("Governorate not found."));
        checkoutService.placeOrder(cart,zone,customer);
        System.out.println("Cart checked out successfully.");
    }

    public void displayPlatformMenu(){
        System.out.println("""
                ========================================
                        Nile Mart Platform
                ========================================""");
        System.out.println("""
                1. Show products.
                2. Create a cart.
                3. Add a product to a cart.
                4. Show a cart.
                5. Check out a cart.
                6.Log out.""");
        System.out.println("=======================");
    }

    public void platformMenu(Customer customer){
        int choice;
        do {
            displayPlatformMenu();
            choice = InputValidation.setInt("Enter your choice: ");
            try {
                switch (choice){
                    case 1->productService.findAll();
                    case 2->{
                        int cartId=cartRepository.createCart(customer);
                        System.out.println("Cart created with ID: "+cartId);
                    }
                    case 3->addProductToCart();
                    case 4->showCart();
                    case 5->checkout(customer);
                    case 6-> System.out.println("thank you for visiting our platform.");
                    default->System.out.println("invalid choice, choose from the menu");
                }
            }catch (StoreException e){
                System.out.println(e.getMessage());
            }

        }while(choice!=6);
    }


}
