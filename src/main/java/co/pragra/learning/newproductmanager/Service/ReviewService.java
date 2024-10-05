package co.pragra.learning.newproductmanager.Service;

import co.pragra.learning.newproductmanager.entity.Product;
import co.pragra.learning.newproductmanager.entity.Review;
import co.pragra.learning.newproductmanager.entity.User;
import co.pragra.learning.newproductmanager.exception.InvalidProductException;

import co.pragra.learning.newproductmanager.exception.InvalidReviewException;
import co.pragra.learning.newproductmanager.exception.ReviewNotFoundException;
import co.pragra.learning.newproductmanager.repo.ReviewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class ReviewService {
    private final ProductService productService;

    private final ReviewRepo reviewRepo;

    private final userService userService;

    public Review addReview(Review review, UUID productId) {
        if (null == review) {
            throw new RuntimeException("Review can't be null");
        }


        if (null != productId) {
            Optional<Product> productOptional = productService.findById(productId);
            if (productOptional.isPresent()) {
                if (review.getUser() != null) {

                    Optional<User> userOptional = userService.findById(review.getUser().getId());
                    User user = userOptional.orElseThrow(() -> new InvalidProductException("User not found"));
                    review.setUser(user);


                }
                Review savedReview = reviewRepo.save(review);

                Product dbProduct = productOptional.get();

                List<Review> reviews = dbProduct.getReviews();

                reviews.add(savedReview);

                dbProduct.setReviews(reviews);

                productService.updateProduct(dbProduct);

                return savedReview;

            }
        } else {
            throw new InvalidProductException("Invalid product id " + productId.toString());

        }
        return null;

    }

    public Review updateReview(Review review) {
        if (null == review || review.getReviews().isEmpty() || review.getReviews().length() <= 10) {

            throw new InvalidReviewException("Review can't be empty or null or less than 10");
        }
            Optional<Review> reviewOptional = reviewRepo.findById(review.getId());
            Review dbReview = reviewOptional.orElseThrow(() -> new ReviewNotFoundException("Review not found"));
            dbReview.setReviews(review.getReviews());
            return reviewRepo.save(dbReview);


        }

    }
