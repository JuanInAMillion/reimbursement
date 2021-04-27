
package com.revature.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateSessionFactory {
	private static SessionFactory sessionFactory;
	private static final Logger LOGGER = LogManager.getFormatterLogger(HibernateSessionFactory.class);
	private HibernateSessionFactory() {
		
	}
    public static synchronized SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try{       	
            	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml")
                        .build();

                sessionFactory = new MetadataSources( serviceRegistry )
                            .buildMetadata()
                            .buildSessionFactory();	
            }catch(HibernateException e) {
            	e.printStackTrace();
            	LOGGER.error("Error creating Session: ",e);
            }
        }
        return sessionFactory;
    }	
    
	public static Session getSession() {
		if(sessionFactory == null) {
			sessionFactory = new Configuration().configure()
					.setProperty("hibernate.connection.url", "jdbc:postgresql://jan25instance.cxiq0hmzrebq.us-east-2.rds.amazonaws.com:5432/postgres")
					.setProperty("hibernate.connection.username", "postgres")
					.setProperty("hibernate.connection.password", "password")
					.buildSessionFactory();			
		}
		return sessionFactory.getCurrentSession();
	}
	
    public static void closeFactory() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException e) {
            	LOGGER.error("Couldn't close SessionFactory", e);
            }
		}
	}
}
