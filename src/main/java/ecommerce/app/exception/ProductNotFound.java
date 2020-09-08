package ecommerce.app.exception;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(Long id){
        super ("Product not found with ID: " + id);
    }
}
