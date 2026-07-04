package com.healthcare.tester;

import static com.healthcare.utils.HibernateUtils.getFactory;

import java.time.LocalDateTime;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.AppointmentDao;
import com.healthcare.dao.AppointmentDaoImpl;

public class BookNewAppointment {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			// 1. create appointment dao instance
			AppointmentDao appointmentDao = new AppointmentDaoImpl();
			// 2. accept details
			System.out.println("Enter doctor id , patient id , appointmentStart");
			// invoke dao's method
			System.out.println(appointmentDao.bookPatientAppointment(sc.nextLong(), sc.nextLong(), LocalDateTime.parse(sc.next())));
		} // JVM - sfc.close() => clean up DBCP
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
