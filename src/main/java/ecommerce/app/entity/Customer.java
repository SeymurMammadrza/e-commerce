package ecommerce.app.entity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private long customerId;

    @Column(name="name")
    private String name;

    @Column(name="phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.EAGER)
    @ToString.Exclude
    private List <Order> orders=new ArrayList<>();

    public long getCustomerId() {
        return customerId;
    }
}
