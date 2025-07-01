package hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainCreateOrder {
    
    public static void main(String[] args) {
        
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
        
        Session session = factory.getCurrentSession();
        
        try {
            
            Order order = new Order("Jane Smith");
            
            Product product1 = new Product("Pizza Margherita", 15.99);
            Product product2 = new Product("Coca Cola", 2.99);
            
            // Add products to order
            order.addProduct(product1);
            order.addProduct(product2);
            
            session.beginTransaction();
            
            System.out.println("Creating order: " + order);
            System.out.println("Creating products:");
            System.out.println("  " + product1);
            System.out.println("  " + product2);
            
            session.save(order);
            session.save(product1);
            session.save(product2);
            
            session.getTransaction().commit();
            
            System.out.println("Order and Products created successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            factory.close();
        }
        
        System.out.println("Finished!");
    }
}