package co.pragra.learning.newproductmanager.controller;

import co.pragra.learning.newproductmanager.Service.ProductService;
import co.pragra.learning.newproductmanager.entity.Product;



import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController // controller & response body
@AllArgsConstructor
@Slf4j

public class ProductRestController {

private final ProductService service;
private final RestTemplate restTemplate;


    @PostMapping("/api/product")
    public Product createProduct(@RequestBody Product product ) {
        return this.service.createProduct(product);

    }
   /* @GetMapping("/api/product")
    public List<Product> getAllProducts() {
        return this.service.findAll();
    }*/

    @GetMapping("/api/product")
    public List<Product> getALl(
            @RequestParam(value = "cost", required = false) Optional<Double> cost,
            @RequestHeader(value = "User-Agent") String userAgent,
            @RequestHeader(name = "X_MARKET" , defaultValue = "CANADA") String market

    ){
        log.info("Following is user agent {} from request", userAgent);
        String forObject = restTemplate.getForObject("https://api.currencyapi.com/v3/latest?apikey=cur_live_wgBJREhUVliLvXUaO6Lkh6gNTXgWRzIl0EsTiknV&currencies=INR&base_currency=CAD", String.class);
        log.info("Got following user api {} from request", forObject);
        if(cost.isPresent()){
            return service.findProductLessThanPrice(cost.get());
        }
        if(market != null && market.equals("INDIA")){
            List<Product> all = this.service.findAll();
            return all.stream().map(p-> { p.setPrice(p.getPrice()*61); return p;}).collect(Collectors.toList());
        }
        return  this.service.findAll();
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
