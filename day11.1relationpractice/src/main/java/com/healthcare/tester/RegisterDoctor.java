package com.healthcare.tester;

import java.time.LocalDate;
import java.util.Scanner;

import com.healthcare.dao.DoctorDao;
import com.healthcare.dao.DoctorDaoImpl;
import com.healthcare.entities.Doctor;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;


public class RegisterDoctor {

	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("firstName,lastName,email,password,phone,dob,regAmount");
			User user=new User(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), LocalDate.parse(sc.next()), sc.nextInt(), UserRole.ROLE_DOCTOR);
			
			System.out.println("appointmentTime,experienceInYears,fees,qualification,speciality");
			Doctor doctor=new Doctor(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.next(), sc.next());
			
			doctor.setUser(user);
			
			DoctorDao doctordao=new DoctorDaoImpl();
			System.out.println(doctordao.registerDoctor(doctor));
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
