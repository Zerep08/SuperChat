/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbu;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author RigoBono
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory; 
    private static final ThreadLocal localSession; 
    
    static {
        try {
           Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml"); 
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
            applySettings(config.getProperties());
            sessionFactory = config.buildSessionFactory(builder.build()); 
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        localSession = new ThreadLocal(); 
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
     public static Session getLocalSession() {
        Session session = (Session) localSession.get(); 
        if (session == null) {
            session = sessionFactory.openSession(); 
            localSession.set(session);              
            System.out.println("\nsesion iniciada");
        }
        return session;
    }
     
     public static void closeLocalSession() {
        Session session = (Session) localSession.get(); 
        if (session != null) session.close();   
        localSession.set(null);                 
        System.out.println("sesion cerrada\n");
    }
}
