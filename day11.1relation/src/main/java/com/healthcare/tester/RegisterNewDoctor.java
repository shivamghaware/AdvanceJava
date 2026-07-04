package com.healthcare.tester;
import static com.healthcare.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.DoctorDao;
import com.healthcare.dao.DoctorDaoImpl;
import com.healthcare.entities.Doctor;
import com.healthcare.entities.UserDetails;
import com.healthcare.entities.UserRole;

public class RegisterNewDoctor {

	public static void main(String[] args) {
		try(SessionFactory sf=getFactory();
				Scanner sc=new Scanner(System.in)) {
			//1. create doctor dao instance
			DoctorDao docDao=new DoctorDaoImpl();
			//2. accept doctor details - basic user 
			System.out.println("""
					Enter common  details  - 
					 firstName,  lastName,  dob,  email,  password,  phone,
			 regAmount
					""");
			UserDetails user=new UserDetails(sc.next(), sc.next(), LocalDate.parse(sc.next()), sc.next(), sc.next(), sc.next(), sc.nextInt(), UserRole.ROLE_DOCTOR); //transient user- not yet persistent
			System.out.println("Enter Doctor specific details - qualifications,  speciality,  experienceInYears,  appointmentTime,  fees ");
			Doctor doctor=new Doctor(sc.next(), sc.next(), sc.nextInt(), sc.nextInt(),sc.nextInt());
			//establish association - Doctor 1--->1 UserDetails
			doctor.setUserDetails(user);
			//invoke dao's method
			System.out.println(docDao.registerDoctor(doctor));
		} //JVM - sfc.close() => clean up DBCP
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
