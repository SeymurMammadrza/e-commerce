package ecommerce.app.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties("customer")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;


    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.REMOVE,orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<OrderItem> orderItems = new ArrayList<>();

}
