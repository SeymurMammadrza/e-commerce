package ecommerce.app.service;

import ecommerce.app.entity.Customer;
import ecommerce.app.entity.Order;
import ecommerce.app.entity.OrderItem;
import ecommerce.app.repository.OrderItemRepository;
import ecommerce.app.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(Long id) {
        return orderRepository.getOne(id);
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public void deleteOrderById(Long id) {
        orderRepository.delete(getById(id));
    }

    public List<OrderItem> findOrderItemsByOrder(Order order) {
        List<OrderItem> orderItemList = orderItemRepository.findAllByOrder(order);
        return orderItemList;
    }

    public Customer getCustomerByOrder(Order order) {
        return orderRepository.CustomerByOrder(order);
    }

    public Order OrderByOrderItem(OrderItem orderItem) {
        return orderItemRepository.OrderByOrderItem(orderItem);
    }
}

