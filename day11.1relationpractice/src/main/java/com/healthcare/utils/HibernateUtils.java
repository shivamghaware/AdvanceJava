package com.healthcare.utils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
	public static SessionFactory factory;
	static {
		factory=new Configuration().configure().buildSessionFactory();
	}
	public static SessionFactory getFactory() {
		return factory;
	}
}
