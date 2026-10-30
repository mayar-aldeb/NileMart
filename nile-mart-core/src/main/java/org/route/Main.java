package org.route;

import org.route.exception.StoreException;
import org.route.model.Customer;
import org.route.repository.CustomerRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    void main(){
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
        Platform platform = context.getBean(Platform.class);

        int choice;
        do{
            menu();
            choice =InputValidation.setInt("Enter your choice: ");
             if(choice==1){
                int customerId =InputValidation.setInt("Enter Customer ID: ");
                try {
                    Customer customer=customerRepository.findById(customerId)
                            .orElseThrow(()-> new StoreException("Customer not found."));
                    platform.platformMenu(customer);
                }catch(StoreException e){
                    System.out.println(e.getMessage());
                }
            }
        }while (choice!=2);

        context.close();
    }

public void menu(){
    System.out.println("""
                ========================================
                        Nile Mart Platform
                ========================================""");
    System.out.println("""
                1.Log in.
                2.Exit.""");
    System.out.println("=======================");
}

}
