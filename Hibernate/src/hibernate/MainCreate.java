package hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainCreate {
    
    public static void main(String[] args) {
        
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Professor.class)
                .buildSessionFactory();
        
        Session session = factory.getCurrentSession();
        
        try {
            
            Customer customer = new Customer("John Doe", "123 Main St");
            Professor professor = new Professor("CS-101", "Software Engineering");
            
            // Establish bidirectional relationship
            customer.setProfessor(professor);
            professor.setCustomer(customer);
            
            session.beginTransaction();
            
            System.out.println("Creating customer: " + customer);
            System.out.println("Creating professor: " + professor);
            
            session.save(customer); 
            
            session.getTransaction().commit();
            
            System.out.println("Customer and Professor created successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            factory.close();
        }
        
        System.out.println("Finished!");
    }
}