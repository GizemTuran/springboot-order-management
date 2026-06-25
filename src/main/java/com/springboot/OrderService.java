package com.springboot;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.springboot.OrderStatus.COMPLETED;
import static com.springboot.OrderStatus.CREATED;

@Service
public class OrderService {

    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(ProductService productService, OrderRepository orderRepository, ProductRepository productRepository){
        this.productService=productService;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    private final List<Order> orders = new ArrayList<>(

            List.of(
                    new Order(1L,1L,"Laptop",5,70000.00,CREATED,70000.00),
                    new Order(2L,2L,"Telefon",15,60000.00,COMPLETED,60000.00)
            )
    );

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Order getById(Long id){

        /*for(Order order:orders){
            if(order.getId().equals(id)){
                return order;
            }
        }
        return null;*/
        return orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException(id));
    }

    public Order createOrder(Order order) {

        System.out.println("OrderService createOrder çalıştı");

        Product product = productService.getById(order.getProductId());
        //product'ın id'si order'ın productId'sine eşit olmalı

        System.out.println("Product bulundu: " + product.getName());

        if (product.getAmount() >= order.getQuantity()) {

            System.out.println("Stok yetersiz exception fırlatılacak");

            throw new InsufficientStock(product.id);
        }

            order.setProductName(product.getName());
            order.setUnitPrice(product.getPrice());
            order.setTotalPrice(product.getPrice() * order.getQuantity());
            order.setStatus(OrderStatus.CREATED);


        return orderRepository.save(order);

    }

    public Order updateOrder(Long id, Order order){

        /*Order updatedOrder = orders.stream()
                .filter(currentOrder->currentOrder.getId().equals(id))
                .findFirst()
                .orElse(null);*/

        Order updatedOrder = orderRepository.findById(id)
                .orElseThrow(()->new OrderNotFoundException(id));

        Product product = productService.getById(order.getProductId());

            updatedOrder.setQuantity(order.getQuantity());
            updatedOrder.setUnitPrice(product.getPrice());
            updatedOrder.setTotalPrice(product.getPrice()*order.getQuantity());
            updatedOrder.setStatus(OrderStatus.COMPLETED);

        return orderRepository.save(updatedOrder);
    }

    public void deleteOrder(Long id){

       /* Order deletedOrder = orders.stream()
                .filter(currentOrder-> currentOrder.getId().equals(id))
                .findFirst()
                .orElse(null);*/

        //yukarıdaki streamde orders listesinde kullanıcının girdiği order bulunmaya
        //çalışılıyor fakat JPA devreye girdiğinde bunu database'te tablolarda orderlar
        //tutulduğu için id ile direkt select sorgusu ile arama yapılarak gerçekleştiriliyor (notes 20062026 göz at)

        Order deletedOrder = orderRepository.findById(id)
                .orElseThrow(()->new OrderNotFoundException(id));

        /*if (deletedOrder!=null){
            orders.remove(deletedOrder);
        }*/
            orderRepository.delete(deletedOrder);


    }


}
