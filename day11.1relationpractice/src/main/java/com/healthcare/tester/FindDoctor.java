package com.healthcare.tester;

import java.time.LocalDate;
import java.util.Scanner;

import com.healthcare.dao.DoctorDao;
import com.healthcare.dao.DoctorDaoImpl;
import com.healthcare.entities.Doctor;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;


public class FindDoctor {

	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("Enter ID");
			
			DoctorDao doctordao=new DoctorDaoImpl();
			System.out.println(doctordao.getDoctorDetails(sc.nextLong()));
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
