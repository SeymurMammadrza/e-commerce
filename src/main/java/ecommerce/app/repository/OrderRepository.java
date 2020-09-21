package ecommerce.app.repository;

import ecommerce.app.entity.Customer;
import ecommerce.app.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer(Customer customer);
    default Customer CustomerByOrder(Order order){
        return order.getCustomer();
    }
}
