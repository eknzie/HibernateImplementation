package hibernate;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="product")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="name", nullable = false)
    private String name;
    
    @Column(name="price", nullable = false)
    private Double price;
    
    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();
    
    public Product() {}
    
    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }
    
    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public Set<Order> getOrders() { return orders; }
    public void setOrders(Set<Order> orders) { this.orders = orders; }
    
    @Override
    public String toString() {
        return "Product: " + name + " - $" + price;
    }
}

