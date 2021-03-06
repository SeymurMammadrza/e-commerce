package ecommerce.app.repository;

import ecommerce.app.entity.OrderItem;
import ecommerce.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
