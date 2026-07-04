package com.healthcare.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.DoctorDao;
import com.healthcare.dao.DoctorDaoImpl;
import com.healthcare.entities.Doctor;
import com.healthcare.utils.HibernateUtils;

public class GetDoctorDetails {

	public static void main(String[] args) {
		try (SessionFactory sf = HibernateUtils.getFactory(); Scanner sc = new Scanner(System.in)) {
			// 1. create dao instance
			DoctorDao doctorDao = new DoctorDaoImpl();
			// 2. accept doctor id
			System.out.println("Enter doctor id");
			// 3. invoke method of the DAO
			Doctor doctor = doctorDao.getDoctorDetails(sc.nextLong());
			System.out.println("Doctor specific details "+doctor);
		//System.out.println("Doctor common details "+doctor.getUserDetails());
			System.out.println("Appointments -");
			doctor.getAppointments().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
