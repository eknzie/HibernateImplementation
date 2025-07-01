package hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainCreateProduct {
    
    public static void main(String[] args) {
        
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
        
        Session session = factory.getCurrentSession();
        
        try {
            
            Product product = new Product("Hawaiian Pizza", 18.99);
            
            session.beginTransaction();
            
            System.out.println("Creating product: " + product);
            
            session.save(product);
            
            session.getTransaction().commit();
            
            System.out.println("Product created successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            factory.close();
        }
        
        System.out.println("Finished!");
    }
}

