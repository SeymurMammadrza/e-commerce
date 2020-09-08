package ecommerce.app.service;

import ecommerce.app.entity.Order;
import ecommerce.app.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
}
