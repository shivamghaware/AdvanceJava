package com.healthcare.tester;

import java.time.LocalDateTime;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.AppointmentDao;
import com.healthcare.dao.AppointmentDaoImpl;
import com.healthcare.utils.HibernateUtils;

public class BookPatientAppointment {

	public static void main(String[] args) {
		try (SessionFactory sf = HibernateUtils.getFactory(); Scanner sc = new Scanner(System.in)) {
			// 1. create dao instance
			AppointmentDao appointmentDao=new AppointmentDaoImpl();
			// 2. enter inputs to book appointment
			
			System.out.println("Enter  patient id , doctor id , appointment start");			
			// 3. invoke method of the DAO
			System.out.println("Appointment booking  status - " + appointmentDao.bookAppointment(sc.nextLong(), sc.nextLong(), LocalDateTime.parse(sc.next())));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
