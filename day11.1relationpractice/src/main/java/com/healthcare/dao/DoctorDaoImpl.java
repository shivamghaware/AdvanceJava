package com.healthcare.dao;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.healthcare.entities.Doctor;
import com.healthcare.entities.User;
import com.healthcare.utils.HibernateUtils;

public class DoctorDaoImpl implements DoctorDao {

	@Override
	public String registerDoctor(Doctor doctor) {
		String msg = "Fail";

		Session session = HibernateUtils.getFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {
			session.persist(doctor);
			tx.commit();
			msg = "Success id:" + doctor.getId();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return msg;
	}

	@Override
	public Doctor getDoctorDetails(long id) {
		Doctor doctor = null;

		Session session = HibernateUtils.getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		try {
			doctor = session.find(Doctor.class, id);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return doctor;
	}

}
