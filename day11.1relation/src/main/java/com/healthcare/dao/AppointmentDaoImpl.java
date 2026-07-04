package com.healthcare.dao;

import static com.healthcare.utils.HibernateUtils.getFactory;

import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppointmentDaoImpl implements AppointmentDao {

	@Override
	public String bookPatientAppointment(Long doctorId, Long patientId, LocalDateTime appointmentstart) {
		String mesg="Booking failed !!!!";
		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		// 3 try-catch
		try {
			//4. validate doctor : find -> if null throw custom un checked exception
			//5. validate patient : find > if null throw custom un checked exception
			//6. is doctor available ?
			//7. is patient available ?
			/*8. in case of all validations successful
			 *  create new appointment object
			 *  establish many -> one using setter
			 *  session.persist(newAppointment)
			 */
			
			tx.commit();
			mesg="booked appointment , appointment ID";
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
