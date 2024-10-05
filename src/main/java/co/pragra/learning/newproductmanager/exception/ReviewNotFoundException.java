package co.pragra.learning.newproductmanager.exception;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(String reviewNotFound) {
        super(reviewNotFound);
    }
}
