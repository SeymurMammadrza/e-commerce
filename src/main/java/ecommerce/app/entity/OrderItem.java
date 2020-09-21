package ecommerce.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "order_items")
@JsonIgnoreProperties("order")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private long orderItemId;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    @ManyToOne
    @JsonIgnoreProperties("product")
    @ToString.Exclude
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
