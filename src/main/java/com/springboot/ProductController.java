package com.springboot;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


    /*
    Controller görev tanımı:

    - URL'yi karşılamak
    - Parametreyi almak
    - Service'i çağırmak
    - Sonucu döndürmek
    */

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

@GetMapping
    public ResponseEntity<List<Product>> getAll(){

    List<Product> product = productService.getAll();

    return ResponseEntity.ok(product);
}
@GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){

        Product product = productService.getById(id);

        if(product == null){
            return ResponseEntity.notFound().build();
        }

    return ResponseEntity.ok(product);

    //2nd alternative to implement as stream instead of foreach
    /* return products.stream(){
        filter(product->product.getId().equals(id))
        .findFirst()
        .orElse(null);
     */

    //if there exist a database in a real project it should be define as:
    /* public Product getById(@PathVariable Long id){
        return productRepository.findById(id).orElse(null);
        }
     */
}

@PostMapping()
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){

    System.out.println("ProductController getById çalıştı");

    Product createdProduct = productService.createProduct(product);

    if(createdProduct == null){
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(createdProduct);
}

@PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@Valid @PathVariable Long id, @RequestBody Product updatedProduct){

        Product upProduct = productService.updateProduct(id,updatedProduct);

    if(upProduct == null){
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(upProduct);
}
@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

    productService.deleteProduct(id);

    return ResponseEntity.noContent().build();
    }
}

