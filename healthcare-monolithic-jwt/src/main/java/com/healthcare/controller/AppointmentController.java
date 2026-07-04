package com.healthcare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.dtos.BookAppointment;
import com.healthcare.service.AppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
	// depcy
	private final AppointmentService appointmentService;

	/*
	 * 5. List Upcoming patient appointments
	 * 
	 * i/p : user id (path variable ) URL -
	 * http://host:port/appointments/patients/{patientId}/upcoming Method - GET Path
	 * var- patient id Response - list of upcoming appointment dtos
	 */
	@GetMapping("/patients/{patientId}/upcoming")
	public ResponseEntity<?> getPatientUpcomingAppointments(@PathVariable Long patientId) {
		System.out.println("in get pat upcoming appointments " + patientId);

		// invoke service layer
		return ResponseEntity.ok(appointmentService.getPatientAppointments(patientId));

	}
	/*
	 * Desc - Doctor wants to mark appointment as complete i/p - doctorId (later
	 * from JWT , currently from path variable) , appointmentId - path variable URI
	 * - /appointments/{appointmentId}/doctors/{doctorId}/complete Method - PATCH
	 * Error Response - SC 400 Success Response - SC 200 + Apiresp - success mesg
	 * 
	 */
	@PatchMapping("/{appointmentId}/doctors/{doctorId}/complete")
	public ResponseEntity<?> completeAppointmentByDoctor(@PathVariable Long appointmentId,
			@PathVariable Long doctorId) {
		return ResponseEntity.ok(appointmentService.completeAppointmentByDoctor(appointmentId, doctorId));
	}
	/*
	 * /*
	 * Design API end point in Appointmentcontroller
	 * Desc -Book Patient's Appointment
      URI -/appointments
      Method - POST 
      Payload - request body - request DTO (patientId , docId, datetime)	 
*  Response - ResponseEntity<?>
	 * error resp - SC 400 (invalid doc id | patient id | unavailable appointement) Api resp (DTO) - err mesg
	 * success - SC 201 + resp dto ( appointmentid , doctorName
  "appointmentDateTime"  "status": "SCHEDULED",
  "message")
	 */
	@PostMapping
	@Operation(description = "Book Patient's Appointment")
	public ResponseEntity<?> bookPatientAppointment(@RequestBody BookAppointment request)
	{
		System.out.println("in book appointment "+request);
	
			return ResponseEntity.status(HttpStatus.CREATED)//SC 201
					.body(appointmentService.bookPatientAppointment(request));

	}
	/*
	 * Desc - Patient wants to cancel appointment i/p - patientId (later from JWT ,
	 * currently from path variable) , appointmentId - path variable URI -
	 * /appointments/{appointmentId}/patients/{patientId}/cancel Method - PATCH
	 * Error Response - SC 400 Success Response - SC 200 + Apiresp - success mesg
	 * 
	 */
	@PatchMapping("/{appointmentId}/patients/{patientId}/cancel")
	public ResponseEntity<?> cancelAppointmentByPatient(@PathVariable Long appointmentId,
			@PathVariable Long patientId) {
		return ResponseEntity.ok(appointmentService.cancelAppointmentByPatient(appointmentId, patientId));
	}




}
