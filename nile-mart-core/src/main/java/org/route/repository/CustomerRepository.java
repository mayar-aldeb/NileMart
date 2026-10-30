package org.route.repository;

import jakarta.annotation.PostConstruct;
import org.route.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class CustomerRepository {

    private final HashMap<Integer,Customer> customers=new HashMap<>();

    @PostConstruct
    public void init(){
        Customer customer = new Customer(1,"Mayar Mostafa","01234567891","mayar.mostafa@gmail.com");
        customers.put(1,customer);

        customer = new Customer(2,"Ali","0123456789",null);
        customers.put(2,customer);
    }

    public void save(Customer customer) {
        customers.put(customer.id(),customer);
    }

    public Optional<Customer> findById(int id) {
        return Optional.ofNullable(customers.get(id));
    }


}
