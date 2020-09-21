package ecommerce.app.repository;

import ecommerce.app.entity.Customer;
import ecommerce.app.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    default long getCustomerId(Customer customer) {
        return customer.getCustomerId();
    }
}
