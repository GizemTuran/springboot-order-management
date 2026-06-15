package com.springboot;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>(

            List.of(
                    new Product(1L,"Laptop",100.00,50),
                    new Product(2L,"Telefon",150.00,30),
                    new Product(3L,"Kulaklık",50.00,20)

            )
    );

    public List<Product> getAll(){
        return products;

    }

    public Product getById(Long id) {

        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }

        return null;
    }

    public Product createProduct(Product product){
        products.add(product);
        return product;
    }

    public Product updateProduct(Long id, Product updatedProduct){
        for(Product product : products){
            if(product.getId().equals(id)){

                product.setAmount(updatedProduct.getAmount());
                product.setPrice(updatedProduct.getPrice());

                return product;
            }
        }
        return null;
    }

    public List<Product> deleteProduct(Long id){

        Product foundProduct = products.stream()
                .filter(currentProduct->currentProduct.getId().equals(id))
                .findFirst()
                .orElse(null);

        if(foundProduct!=null){
            products.remove(foundProduct);
        }
        return products;
    }


}
