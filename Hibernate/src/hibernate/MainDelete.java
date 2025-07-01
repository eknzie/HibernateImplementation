package hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainDelete {
    
    public static void main(String[] args) {
        
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Professor.class)
                .buildSessionFactory();
        
        Session session = factory.getCurrentSession();
        
        try {
            
            session.beginTransaction();
            
            Customer customer = session.get(Customer.class, 1);
            
            if (customer != null) {
                System.out.println("Deleting customer: " + customer);
                if (customer.getProfessor() != null) {
                    System.out.println("Also deleting professor: " + customer.getProfessor());
                }
                
                session.delete(customer); // Professor is deleted due to cascade
                
                System.out.println("Customer and Professor deleted successfully!");
            } else {
                System.out.println("Customer not found!");
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

