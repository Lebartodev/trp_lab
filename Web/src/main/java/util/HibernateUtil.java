package util;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

public class HibernateUtil {

    //XML based configuration
    private static SessionFactory sessionFactory;
    @PersistenceContext(unitName = "org.hibernate.tutorial.jpa")
    private static EntityManagerFactory entityManager;

    private static SessionFactory buildSessionFactory() {
        try {
            System.out.println("Hibernate Configuration loaded");

            //ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");

            //SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null) buildSessionFactory();
        return entityManager.createEntityManager();
    }

}