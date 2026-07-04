package com.healthcare.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.custom_exceptions.InvalidInputException;
import com.healthcare.custom_exceptions.ResourceNotFoundException;
import com.healthcare.dtos.ApiResponse;
import com.healthcare.dtos.AppointmentDTO;
import com.healthcare.dtos.BookAppointment;
import com.healthcare.dtos.CompleteAppointmentDetails;
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
	// depcy
	private final AppointmentRepository appointmentRepository;
	private final PatientRepository patientRepository;
	private final DoctorRepository doctorRepository;

	@Override
	public List<AppointmentDTO> getPatientAppointments(Long patientId) {
		return appointmentRepository.getAppointmentDetailsForPatient(patientId, Status.SCHEDULED);
	}

	@Override
	public ApiResponse bookPatientAppointment(BookAppointment request) {
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
		Appointment newAppointment = new Appointment(startTime, endTime);
		// 7 Appointment *---->1 Patient
		newAppointment.setMyPatient(patient);
		// 8. Appointment *<-----> 1Doctor
		doctor.getAppointments().add(newAppointment);
		newAppointment.setMyDoctor(doctor);
		// explicit save is not required - cascade
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

	@Override
	public ApiResponse cancelAppointmentByPatient(Long appointmentId, Long patientId) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not found : invalid id"));
		if (!appointment.getMyPatient().getId().equals(patientId))
			throw new InvalidInputException("Appointment does not belong to this patient!!!");
		if (appointment.getStatus() != Status.SCHEDULED)
			throw new InvalidInputException("Only scheduled appointments can be cancelled!!!");
		appointment.setStatus(Status.CANCELLED);
		return new ApiResponse("Success", "Appointment Cancelled !!");
	}

	@Override
	public ApiResponse completeAppointmentByDoctor(Long appointmentId, Long doctorId) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not found : invalid id"));
		if (!appointment.getMyDoctor().getId().equals(doctorId))
			throw new InvalidInputException("Appointment does not belong to this doctor!!!");
		/*
		 * Commented only for current testing if (appointment.getStatus() !=
		 * Status.SCHEDULED ||
		 * appointment.getEndDateTime().isAfter(LocalDateTime.now())) throw new
		 * InvalidInputException(
		 * "Only scheduled appointments whose consultation is done , can be completed!!!"
		 * );
		 */
		appointment.setStatus(Status.COMPLETED);
		return new ApiResponse("Success", "Appointment Completed !!");
	}

	@Override
	public List<CompleteAppointmentDetails> getAllAppointments() {

		return appointmentRepository.getAllAppointments();
	}

}
