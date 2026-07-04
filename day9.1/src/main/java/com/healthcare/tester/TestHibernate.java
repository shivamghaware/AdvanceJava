package com.healthcare.tester;
import org.hibernate.*;
import static com.healthcare.utils.HibernateUtils.getFactory;

public class TestHibernate {

	public static void main(String[] args) {
		try(SessionFactory sf=getFactory()) {
			System.out.println("SF created -> Hibernate is up & running !!!"+sf);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
