package com.springboot;

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
    public List<Product> getAll(){

    return productService.getAll();

}
@GetMapping("/{id}")
    public Product getById(@PathVariable Long id){

    return productService.getById(id);

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
    public Product createProduct(@RequestBody Product product){
    return productService.createProduct(product);
}

@PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct){

    return productService.updateProduct(id,updatedProduct);
}
@DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){

    productService.deleteProduct(id);
    }
}

