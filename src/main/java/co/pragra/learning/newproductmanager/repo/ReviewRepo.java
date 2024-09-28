package co.pragra.learning.newproductmanager.repo;

import co.pragra.learning.newproductmanager.entity.Product;
import co.pragra.learning.newproductmanager.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {

}
