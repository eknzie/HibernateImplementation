package hibernate;
import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="name", nullable = false)
    private String name;
    
    @Column(name="address", nullable = false)
    private String address;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;
    
    public Customer() {}
    
    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }
    
    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }
    
    @Override
    public String toString() {
        return "Customer: " + name + ", " + address;
    }
}
