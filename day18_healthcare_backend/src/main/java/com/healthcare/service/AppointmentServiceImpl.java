package com.healthcare.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.healthcare.controller.PatientController;
import com.healthcare.custom_exceptions.InvalidInputException;
import com.healthcare.custom_exceptions.ResourceNotFoundException;
import com.healthcare.dtos.ApiResponse;
import com.healthcare.dtos.AppointmentResp;
import com.healthcare.dtos.BookAppointment;
import com.healthcare.entities.Appointment;
import com.healthcare.entities.Doctor;
import com.healthcare.entities.Patient;
import com.healthcare.entities.Status;
import com.healthcare.repository.AppointmentRepository;
import com.healthcare.repository.DoctorRepository;
import com.healthcare.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

	private final PatientController patientController;
	private final AppointmentRepository appointmentRepository;
	private final PatientRepository patientRepository;
	private final DoctorRepository doctorRepository;

	

	@Override
	public List<AppointmentResp> getUpcomingPatientAppointments(Long patientId) {
		// call dao's method
		return appointmentRepository.getPatientAppointments(patientId, Status.SCHEDULED);
	}

	@Override
	public ApiResponse bookAppointment(BookAppointment request) {
		Long patientId = request.getPatientId();
		Long docId = request.getDoctorId();
		LocalDateTime startTime = request.getStart();

		/*
		 * 1. validate patient - if not throw custom un checked exception
		 */
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Patient ID !!!!!"));
		// 2. Validate doctor
		Doctor doctor = doctorRepository.findById(docId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Doctor ID !!!!!"));
		// 3. appointment end time
		LocalDateTime endTime = startTime.plusMinutes(doctor.getAppointmentTime());
		// 4. check doc's availability
		if (isDoctorUnAvailable(docId, endTime, startTime))
			throw new InvalidInputException("doctor not available");
		// 5. check patient's availability
		if (isPatientUnAvailable(patientId, endTime, startTime))
			throw new InvalidInputException("patient not available");
		// 6. create appointment - transient
		Appointment newAppointment=new Appointment(startTime,endTime);
		//7 Appointment *---->1 Patient
		newAppointment.setMyPatient(patient);
		//8. Appointment *<-----> 1Doctor
		doctor.getAppointments().add(newAppointment);
		newAppointment.setMyDoctor(doctor);
		//explicit save is not required - cascade
		return new ApiResponse("Booked Appointment !", "Success");
	}

	private boolean isDoctorUnAvailable(Long docId, LocalDateTime end, LocalDateTime start) {
		// TODO Auto-generated method stub
		return appointmentRepository.existsByMyDoctorIdAndStatusAndStartDateTimeBeforeAndEndDateTimeAfter(docId,
				Status.SCHEDULED, end, start);
	}

	private boolean isPatientUnAvailable(Long patientId, LocalDateTime end, LocalDateTime start) {
		// TODO Auto-generated method stub
		return appointmentRepository.existsByMyPatientIdAndStatusAndStartDateTimeBeforeAndEndDateTimeAfter(patientId,
				Status.SCHEDULED, end, start);
	}

}
