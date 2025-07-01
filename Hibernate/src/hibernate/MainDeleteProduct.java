package hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainDeleteProduct {
    
    public static void main(String[] args) {
        
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
        
        Session session = factory.getCurrentSession();
        
        try {
            
            session.beginTransaction();
            
            Product product = session.get(Product.class, 1);
            
            if (product != null) {
                System.out.println("Deleting product: " + product);
                System.out.println("Orders will remain in database");
                
                session.delete(product); // Orders remain due to no cascade delete
                
                System.out.println("Product deleted successfully! Orders remain.");
            } else {
                System.out.println("Product not found!");
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