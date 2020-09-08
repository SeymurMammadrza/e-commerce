package ecommerce.app.exception;

public class OrderItemNotFound extends RuntimeException {
    public OrderItemNotFound(Long id){
        super ("Order item not found with ID: " + id);
    }
}
