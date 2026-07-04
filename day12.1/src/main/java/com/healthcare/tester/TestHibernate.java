package com.healthcare.tester;

import org.hibernate.SessionFactory;

import com.healthcare.utils.HibernateUtils;

public class TestHibernate {

	public static void main(String[] args) {
		try (SessionFactory sf = HibernateUtils.getFactory()) {
			System.out.println("SF created , Hibernate up & running .....");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
