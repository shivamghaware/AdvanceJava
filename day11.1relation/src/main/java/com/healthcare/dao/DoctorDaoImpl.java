package com.healthcare.dao;

import static com.healthcare.utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.healthcare.entities.Doctor;

public class DoctorDaoImpl implements DoctorDao {

	@Override
	public String registerDoctor(Doctor doctor) {
		String mesg="reg failed...";
		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		// 3 try-catch
		try {
	//		session.persist(doctor.getUserDetails());
			session.persist(doctor);
			tx.commit();
			mesg="doctor registered with id ="+doctor.getId();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
				// re throw the same exception to the caller
				throw e;
			}
		}
		return mesg;
	}

}
