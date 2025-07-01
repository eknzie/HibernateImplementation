package hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainDeleteOrder {
    
    public static void main(String[] args) {
        
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
        
        Session session = factory.getCurrentSession();
        
        try {
            
            session.beginTransaction();
            
            Order order = session.get(Order.class, 1);
            
            if (order != null) {
                System.out.println("Deleting order: " + order);
                System.out.println("Products will remain in database");
                
                session.delete(order); // Products remain due to no cascade delete
                
                System.out.println("Order deleted successfully! Products remain.");
            } else {
                System.out.println("Order not found!");
            }
            
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            factory.close();
        }
        
        System.out.println("Finished!");
    }
}