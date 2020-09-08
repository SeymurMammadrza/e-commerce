package ecommerce.app.exception;

public class OrderNotFound extends RuntimeException {
    public OrderNotFound(Long id){
        super ("Order not found with ID: " + id);
    }
}
