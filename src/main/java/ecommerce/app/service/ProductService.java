package ecommerce.app.service;

import ecommerce.app.entity.Customer;
import ecommerce.app.entity.Order;
import ecommerce.app.entity.OrderItem;
import ecommerce.app.entity.Product;
import ecommerce.app.repository.OrderItemRepository;
import ecommerce.app.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;
    OrderItemRepository orderItemRepository;

    public ProductService(ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.getOne(id);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.delete(getById(id));
    }


}
