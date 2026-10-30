package org.route.repository;


import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.route.model.Order;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class OrderRegistry {

    private final Map<String, Order> orders=new HashMap<>();
    private static int orderCount=0;



    public void save(Order order){
        orders.put(order.getOrderNumber(),order);
    }

    public String createOrderId(){
        return "NM-"+
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))+
                "-"+String.format("%05d",++orderCount);
    }

    @PreDestroy
    public void destroy() {
        if(orders.isEmpty()) {
            System.out.println("No orders found");
            return;
        }
        System.out.print(orders.size()+" orders, ");
        System.out.println("revenue "+
                orders.values().stream().mapToDouble(order->order.getTotal().doubleValue()).sum()+" EGP.");
    }


}
