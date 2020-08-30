package ecommerce.app.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int cartId;

    @Column(name = "quantity")
    private int quantity;

    @OneToMany (mappedBy = "cart")
    private List <Order> ordersByCart;

    @OneToMany(mappedBy = "cart")
    private List <Product> productsByCart;

    

}
