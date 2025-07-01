package hibernate;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="order_table")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="order_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @Column(name="customer_name", nullable = false)
    private String customerName;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "order_detail",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();
    
    public Order() {}
    
    public Order(String customerName) {
        this.customerName = customerName;
        this.date = new Date();
    }
    
    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    
    public Set<Product> getProducts() { return products; }
    public void setProducts(Set<Product> products) { this.products = products; }
    
    public void addProduct(Product product) {
        this.products.add(product);
        product.getOrders().add(this);
    }
    
    public void removeProduct(Product product) {
        this.products.remove(product);
        product.getOrders().remove(this);
    }
    
    @Override
    public String toString() {
        return "Order: " + customerName + " on " + date + " with " + products.size() + " products";
    }
}