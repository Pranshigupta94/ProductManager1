package co.pragra.learning.newproductmanager.controller;

import co.pragra.learning.newproductmanager.Service.ProductService;
import co.pragra.learning.newproductmanager.entity.Product;



import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController // controller & response body
@AllArgsConstructor

public class ProductRestController {

private final ProductService service;


    @PostMapping("/api/product")
    public Product createProduct(@RequestBody Product product ) {
        return this.service.createProduct(product);

    }
    @GetMapping("/api/product")
    public List<Product> getAllProducts() {
        return this.service.findAll();
    }


    @PutMapping("/api/product")
    public Product updateProduct(@RequestBody Product product ) {
        return this.service.updateProduct(product);

    }

    @GetMapping("/api/product/{id}")
    public Optional<Product> getProductById(@PathVariable ("id") String uuid) {

        UUID uuid1 = UUID.fromString(uuid);
        return service.findById(uuid1);

    }
    @GetMapping("/api/product/filter")
    public List<Product> getProductLessThanPrice(@RequestParam(value = "price", required = true) double price) {
        return service.findProductLessThanPrice(price);
    }


}
