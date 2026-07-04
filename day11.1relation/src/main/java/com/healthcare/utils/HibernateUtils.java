package com.healthcare.utils;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory factory;
	static {
		System.out.println("in static init block");
		/*
		 * Create Configuration class instance 
		 *  - default constructor
		 * Configure
		 *  - configure()
		 *   Build SessionFactory (SF)
		 *   - buildSessionFactory
		 */
		factory=new Configuration() //empty config object
				    .configure() //loads the config(props) from hibernate.cfg.xml
				    .buildSessionFactory();//builds singleton instance of SF
	}
	//getter
	public static SessionFactory getFactory() {
		return factory;
	}
	
}
