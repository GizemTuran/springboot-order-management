package com.springboot;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final List<Product> products = new ArrayList<>(

            List.of(
                    new Product(1L,"Laptop",100.00,50),
                    new Product(2L,"Telefon",150.00,30),
                    new Product(3L,"Kulaklık",50.00,20)

            )
    );
    private final OrderRepository orderRepository;

    public ProductService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public List<Product> getAll(){
        return productRepository.findAll();

    }

    public Product getById(Long id) {

        /*for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }

        return null;  */

        //yukarıdaki algoritmayı findById içerisinde yapıyor.

        return productRepository.findById(id).orElse(null);

        /* return içersindeki şunun karşılığı:

        SELECT *
        FROM product
        WHERE id = ?;

        Ama SQL'i Hibernate oluşturuyor.*/
    }

    public Product createProduct(Product product){
        //products.add(product);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct){
        /*for(Product product : products){
            if(product.getId().equals(id)){*/

        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            return null;
        }

        product.setAmount(updatedProduct.getAmount());
        product.setPrice(updatedProduct.getPrice());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id){

        /*Product foundProduct = products.stream()
                .filter(currentProduct->currentProduct.getId().equals(id))
                .findFirst()
                .orElse(null);*/

        Product deletedProduct = productRepository.findById(id).orElse(null);

        if(deletedProduct!=null){
            productRepository.delete(deletedProduct);
        }
    }

}
