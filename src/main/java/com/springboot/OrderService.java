package com.springboot;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.springboot.OrderStatus.COMPLETED;
import static com.springboot.OrderStatus.CREATED;

@Service
public class OrderService {

    private final ProductService productService;

    public OrderService(ProductService productService){
        this.productService=productService;
    }

    private final List<Order> orders = new ArrayList<>(

            List.of(
                    new Order(1L,1L,"Laptop",5,70000.00,CREATED,70000.00),
                    new Order(2L,2L,"Telefon",15,60000.00,COMPLETED,60000.00)
            )
    );

    public List<Order> getAll(){
        return orders;
    }

    public Order getById(Long id){

        for(Order order:orders){
            if(order.getId().equals(id)){
                return order;
            }
        }
        return null;
    }

    public Order createOrder(Order order) {

        Product product = productService.getById(order.getProductId());
        //product'ın id'si order'ın productId'sine eşit olmalı

        if (product == null) {
            return null;
        }

        if (product.getAmount() >= order.getQuantity()) {

            order.setProductName(product.getName());
            order.setQuantity(product.getAmount());
            order.setUnitPrice(product.getPrice());

            order.setTotalPrice(product.getPrice() * order.getQuantity());

            order.setStatus(OrderStatus.CREATED);

        }
        return order;

    }

    public Order updateOrder(Long id, Order order){

        Order updatedOrder = orders.stream()
                .filter(currentOrder->currentOrder.getId().equals(id))
                .findFirst()
                .orElse(null);

        if(updatedOrder!=null){
            updatedOrder.setQuantity(order.getQuantity());
            updatedOrder.setStatus(order.getStatus());
            updatedOrder.setUnitPrice(order.getUnitPrice());
            updatedOrder.setTotalPrice(order.getTotalPrice());
        }

        return updatedOrder;
    }

    public List<Order> deleteOrder(Long id){

        Order deletedOrder = orders.stream()
                .filter(currentOrder-> currentOrder.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (deletedOrder!=null){
            orders.remove(deletedOrder);
        }
        return orders;
    }


}
