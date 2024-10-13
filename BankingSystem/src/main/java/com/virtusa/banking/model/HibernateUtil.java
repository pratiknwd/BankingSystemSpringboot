package com.virtusa.banking.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class HibernateUtil {
	
	
	@Autowired
    private   SessionFactory sessionFactory;

    public Session getSessionFactory() {
        return sessionFactory.openSession();
    }
	
}
