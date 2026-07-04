package com.healthcare.tester;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.DoctorDao;
import com.healthcare.dao.DoctorDaoImpl;
import com.healthcare.entities.Doctor;
import com.healthcare.entities.UserDetails;
import com.healthcare.entities.UserRole;
import com.healthcare.utils.HibernateUtils;

public class RegisterNewDoctor {

	public static void main(String[] args) {
		try (SessionFactory sf = HibernateUtils.getFactory(); Scanner sc = new Scanner(System.in)) {
			// 1. create dao instance
			DoctorDao doctorDao = new DoctorDaoImpl();
			// 2. accept basic details of doctor
			
			System.out.println(
					"Enter user details -  firstName,  lastName,  email,  password,  phone,  regAmount ,  dob");
			UserDetails user = new UserDetails(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.nextInt(),
					LocalDate.parse(sc.next()),UserRole.ROLE_DOCTOR);
			
			System.out.println(
					"Enter doctor specific details - qualifications,  speciality,  experienceInYears,  appointmentTime,  fees");
			Doctor doctor=new Doctor(sc.next(), sc.next(),sc.nextInt(), sc.nextInt(), sc.nextInt());
			//*************** establish one to one association Doctor 1--->1 User
			doctor.setUserDetails(user);
			// 3. invoke method of the DAO
			System.out.println("Registration status - " + doctorDao.registerDoctor(doctor));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
