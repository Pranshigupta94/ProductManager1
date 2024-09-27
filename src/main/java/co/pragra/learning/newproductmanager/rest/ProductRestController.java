package co.pragra.learning.newproductmanager.rest;

import co.pragra.learning.newproductmanager.entity.Product;

import co.pragra.learning.newproductmanager.repo.ProdRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
@RestController // controller & response body
public class ProductRestController {
    @Autowired
    private ProdRepo repo;

 public ProductRestController(ProdRepo repo) {
        this.repo = repo;
    }


    @PostMapping("/api/product")
    public Product createProduct(@RequestBody Product product ) {
        return this.repo.save(product);

    }
    @GetMapping("/api/product")
    public List<Product> getAllProducts() {
        return this.repo.findAll();
    }
}
