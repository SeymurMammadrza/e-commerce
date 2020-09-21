package ecommerce.app.service;

import ecommerce.app.entity.Customer;
import ecommerce.app.entity.Order;
import ecommerce.app.repository.CustomerRepository;
import ecommerce.app.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    CustomerRepository customerRepository;
    OrderRepository orderRepository;

    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getById(Long id) {
        return customerRepository.getOne(id);
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomerById(Long id) {
        customerRepository.delete(getById(id));
    }

    public List<Order> findOrdersByCustomer(Customer  customer) {
        //Customer customer = customerRepository.getOne(id);
        List<Order> orderList = orderRepository.findAllByCustomer(customer);
        System.out.println(orderList);
        return orderList;
    }

    public long getCustomerId(Customer customer) {
        return customerRepository.getCustomerId(customer);
    }
}