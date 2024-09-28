package co.pragra.learning.newproductmanager.repo;

import co.pragra.learning.newproductmanager.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProdRepo extends JpaRepository<Product, UUID> {
    //@Query("SELECT p FROM Product p WHERE p.price < :price")
    //HQL
    @Query("SELECT p from Product p where p.price < :price")
    List<Product> findCheaperProduct(@Param("price") double price);

}
