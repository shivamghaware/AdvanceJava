package com.healthcare.utils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
/*
 * Job - to supply singleton instance of SessionFactory (SF)
 */
public class HibernateUtils {
	private static SessionFactory factory;
	static {
		System.out.println("in static init block");
		/*
		 * Steps
		 * 1. Create Configuration class instance , using default constructor
		 * 2. To load the props from xml config file
		 * Method of Configuration
		 * public Configuration configure() throws HibernateException - 
		 * un checked exception
		 * - returns populated config instance , loaded from xml file
		 * 3. Build SF from Configuration
		 * public SessionFactory buildSessionFactory() throws HibernateException
		 */
		factory=new Configuration() // empty config object
				    .configure() //loads the  config , by reading xml file(props & mapping)
				    .buildSessionFactory();//singleton , immutable => inherent thread safe , heavy weight - takes time to create - DBCP
	}
	//getter
	public static SessionFactory getFactory() {
		return factory;
	}
	

}
