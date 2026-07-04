package com.healthcare.dao;

import static com.healthcare.utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.healthcare.entities.Doctor;

public class DoctorDaoImpl implements DoctorDao {

	@Override
	public String registerDoctor(Doctor doctor) {
		String mesg="doc reg failed!!!!!!!!!!";
		//1. Get Session from SessionFactory
				Session session=getFactory().getCurrentSession();
				//2. Begin a Transaction
				Transaction tx=session.beginTransaction();
				try {
					//explicitly save user details first
			//		session.persist(doctor.getUserDetails());
					//then save doctor details
					session.persist(doctor);
					tx.commit();
					mesg="Registered doc with ID = "+doctor.getId();
				} catch (RuntimeException e) {
					if(tx != null)
					{
						tx.rollback();
						
					}
					//re throw the same exception to the caller
					throw e;
				}
				
		return mesg;
	}

	@Override
	public Doctor getDoctorDetails(Long doctorId) {
		Doctor doctor=null;
		String jpql="select d from Doctor d left join fetch d.appointments where d.id=:did";
		// 1. Get Session from SessionFactory 
		Session session=getFactory().getCurrentSession();
		//2. Begin Tx
		Transaction tx=session.beginTransaction();
		//3 try-catch
		try {
//			doctor=session.find(Doctor.class, doctorId);//select - doctors , users
			
			//doctor - persistent
			//simply access the size of collection
		//	doctor.getAppointments().size();//select - appointments
			doctor=session.createQuery(jpql, Doctor.class)
					.setParameter("did", doctorId)
					.getSingleResult();
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
			{
				tx.rollback();
				//re throw the same exception to the caller
				throw e;
			}
		}
		//doctor - DETACHED
		return doctor;
	}
	

}
