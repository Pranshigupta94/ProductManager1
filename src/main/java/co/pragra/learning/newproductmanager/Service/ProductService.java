package co.pragra.learning.newproductmanager.Service;

import co.pragra.learning.newproductmanager.entity.Product;

import co.pragra.learning.newproductmanager.exception.InvalidProductException;
import co.pragra.learning.newproductmanager.exception.InvalidUserDataException;
import co.pragra.learning.newproductmanager.repo.ProdRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProdRepo repo;


    public Product createProduct(Product product) {
        if (null == product) {
            throw new InvalidProductException("Product can't be null");
        }
        if (product.getProductName() == null || product.getProductName().length() < 5) {
            throw new InvalidUserDataException("Product name can't be null or less than 5 characters");
        }
        product.setReviews(new ArrayList<>());
        return repo.save(product);
    }

    public Product updateProduct(Product product) {
        if (null == product) {
            throw new InvalidProductException("Product can't be null");
        }
        Optional<Product> productOptional = repo.findById(product.getId());
        productOptional.orElseThrow(() -> new InvalidProductException("Product Id not found"));

        Product product1 = productOptional.get();

        if (product.getProductName() != null) {
            product1.setProductName(product.getProductName());
        }
        if (product.getReviews() != null) {
            product1.setReviews(product.getReviews());
        }
        if (product.getDescription() != null) {
            product1.setDescription(product.getDescription());
        }
        if (product.getPrice() != product1.getPrice() && product.getPrice() != 0.0) {
            product1.setPrice(product.getPrice());
        }

        return repo.save(product1);


    }

    public List<Product> findAll() {
        return this.repo.findAll();
    }

    //this method is for individual product
    public Optional<Product> findById(UUID uuid1) {
        return repo.findById(uuid1);
    }

    public List<Product> findProductLessThanPrice(double price) {
        return repo.findCheaperProduct(price);


    }


}
