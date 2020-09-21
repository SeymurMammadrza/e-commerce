package ecommerce.app.repository;

import ecommerce.app.entity.Order;
import ecommerce.app.entity.OrderItem;
import ecommerce.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrder(Order order);

    default Order OrderByOrderItem(OrderItem orderItem) {
        return orderItem.getOrder();
    }
    default Product ProductByOrderItem(OrderItem orderItem){return orderItem.getProduct();}

}