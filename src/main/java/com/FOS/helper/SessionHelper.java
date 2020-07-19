package com.FOS.helper;


import com.FOS.FoodOrderingApplication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionHelper {
    public static Session getSession(){
        SessionFactory sessionFactory = FoodOrderingApplication.getStaticHibernateBundle().getSessionFactory();
        return sessionFactory.openSession();
    }
}
