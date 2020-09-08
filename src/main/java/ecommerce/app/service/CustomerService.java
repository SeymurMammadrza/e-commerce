package ecommerce.app.service;

import ecommerce.app.entity.Customer;
import ecommerce.app.entity.Order;
import ecommerce.app.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public List <Customer> getAll(){
        return customerRepository.findAll();
    }
    public Customer getById(Long id){
        return customerRepository.getOne(id);
    }
    public void saveCustomer(Customer customer){
         customerRepository.save(customer);
    }
    public void deleteCustomerById(Long id){
        customerRepository.delete(getById(id));
    }

}
