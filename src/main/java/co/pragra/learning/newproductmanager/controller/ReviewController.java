package co.pragra.learning.newproductmanager.controller;

import co.pragra.learning.newproductmanager.Service.ReviewService;

import co.pragra.learning.newproductmanager.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/api/product/{id}/review")
    public Review addReview(@RequestBody Review review, @PathVariable ("id") UUID productId) {
        return reviewService.addReview(review, productId);
    }

    @PutMapping("/api/product/review")
    public ResponseEntity<Review> updateReview(@RequestBody Review review) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.updateReview(review));
    }

}
