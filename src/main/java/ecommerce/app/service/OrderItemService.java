package ecommerce.app.service;

import ecommerce.app.entity.Order;
import ecommerce.app.entity.OrderItem;
import ecommerce.app.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
    public List<OrderItem> getAll(){
        return orderItemRepository.findAll();
    }
    public OrderItem getById(Long id){
        return orderItemRepository.getOne(id);
    }
    public void saveOrderItem(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    }
    public void deleteOrderItemById(Long id){
        orderItemRepository.delete(getById(id));
    }
}
