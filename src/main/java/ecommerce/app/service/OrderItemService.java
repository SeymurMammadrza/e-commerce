package ecommerce.app.service;

import ecommerce.app.entity.OrderItem;
import ecommerce.app.entity.Product;
import ecommerce.app.repository.OrderItemRepository;
import ecommerce.app.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    OrderItemRepository orderItemRepository;
    ProductRepository productRepository;

    public OrderItemService(OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    public List<OrderItem> getAll() {
        return orderItemRepository.findAll();
    }

    public OrderItem getById(Long id) {
        return orderItemRepository.getOne(id);
    }

    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public void deleteOrderItemById(Long id) {
        orderItemRepository.delete(getById(id));
    }

    public Product ProductByOrderItem(OrderItem orderItem) {
        return orderItemRepository.ProductByOrderItem(orderItem);
    }
}
