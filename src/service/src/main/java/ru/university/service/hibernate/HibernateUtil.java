package ru.university.service.hibernate;

import java.io.File;
import java.io.InputStream;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private SessionFactory buildSessionFactory() {
        try {
            InputStream file = this.getClass().getClassLoader().getResourceAsStream("SomeTextFile.txt");
            // Create the SessionFactory from hibernate.cfg.xml
            return new AnnotationConfiguration().configure(file).buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}