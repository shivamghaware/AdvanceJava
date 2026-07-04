package com.healthcare.dao;

import static com.healthcare.utils.HibernateUtils.getFactory;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.healthcare.custom_exceptions.AppointmentException;
import com.healthcare.entities.Appointment;
import com.healthcare.entities.Doctor;
import com.healthcare.entities.Patient;
import com.healthcare.entities.Status;

public class AppointmentDaoImpl implements AppointmentDao {

	@Override
	public String bookAppointment(Long patientId, Long doctorId, LocalDateTime appointmentStart) {
		String mesg = "Failed";
		// 1. Get Session from SessionFactory
		Session session = getFactory().getCurrentSession();
		// 2. Begin a Transaction
		Transaction tx = session.beginTransaction();
		try {
			// 3. Get patient details from its id - find -> in case of invalid pid -> throw
			// custom un checked exception
			Patient patient = session.find(Patient.class, patientId);
			if (patient == null)
				throw new AppointmentException("Invalid Patient id!!!!");
			// 4. Get doc details from its id - find -> in case of invalid doc id -> throw
			// custom un checked exception
			Doctor doctor = session.find(Doctor.class, doctorId);
			if (doctor == null)
				throw new AppointmentException("Invalid Doctor id!!!!");
			// 4.1 calculate appoint end time
			LocalDateTime endTime = appointmentStart.plusMinutes(doctor.getAppointmentTime());
			// 5. Check doc's availability -> if unavailable -> throw custom un checked
			// exception
			if (!isDoctorAvailable(doctorId, appointmentStart, endTime))
				throw new AppointmentException("Doctor Unavailable !!!!");

			// 6. Check patient's availability -> if unavailable -> throw custom un checked
			// exception
			if (!isPatientAvailable(patientId, appointmentStart, endTime))
				throw new AppointmentException("Patient Unavailable !!!!");

			/*
			 * 7. => all validations done -> create transient Appointment instance -> set
			 * status : to SCHEDULED (already done in ctor) -> set end date time ->
			 * establish associations -> persist
			 */
			Appointment appointment = new Appointment(appointmentStart);
			appointment.setEndDateTime(endTime);
			// app -> doc
			appointment.setMyDoctor(doctor);
			//doc -> app
			doctor.getAppointments().add(appointment);
			// app -> patient
			appointment.setMyPatient(patient);
			//persist
		//	session.persist(appointment);
			tx.commit();
			mesg = "Appointment booked with ID ";
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		return mesg;
	}

	private boolean isDoctorAvailable(Long doctorId, LocalDateTime appointmentStart, LocalDateTime endTime) {
		Session session = getFactory().getCurrentSession();
		String jpql = """
				   select a.id from Appointment a where
				   a.myDoctor.id =:docId and
				   a.status = :sts and
				   a.startDateTime < :newEnd and
				   a.endDateTime > :newStart
				""";
		List<Long> ids = session.createQuery(jpql, Long.class).setParameter("docId", doctorId)
				.setParameter("sts", Status.SCHEDULED).setParameter("newEnd", endTime)
				.setParameter("newStart", appointmentStart).getResultList();
		return ids.size() == 0;
	}

	private boolean isPatientAvailable(Long patientId, LocalDateTime appointmentStart, LocalDateTime endTime) {
		Session session = getFactory().getCurrentSession();
		String jpql = """
				   select a.id from Appointment a where
				   a.myPatient.id =:patId and
				   a.status = :sts and
				   a.startDateTime < :newEnd and
				   a.endDateTime > :newStart
				""";
		List<Long> ids = session.createQuery(jpql, Long.class).setParameter("patId", patientId)
				.setParameter("sts", Status.SCHEDULED).setParameter("newEnd", endTime)
				.setParameter("newStart", appointmentStart).getResultList();
		return ids.size() == 0;
	}

}
