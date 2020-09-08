package ecommerce.app.exception;

public class CustomerNotFound extends RuntimeException {
    public CustomerNotFound(Long id){
        super ("Customer not found with ID: " + id);
    }
}
