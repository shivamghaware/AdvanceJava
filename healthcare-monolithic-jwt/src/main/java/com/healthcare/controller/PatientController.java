package com.healthcare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.entities.BloodGroup;
import com.healthcare.service.PatientService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@Validated
public class PatientController {
	//dependency
	private final PatientService patientService;
	/*
	 * Design API end point in Patient controller
	 * Desc -Get Patient Specific Details
      URI -/patients/{userId}
      Method - GET 
      path variable(URI template var) 	 
*  Response - ResponseEntity<?>
	 * error resp - SC 404 (in case of invalid user id) + Api resp (DTO) - err mesg
	 * success - SC 200 + PatientDTO
	 */
	@GetMapping("/{patientId}")
	public ResponseEntity<?> getPatientDetails(@PathVariable @Min(5) @Max(8)
			Long patientId) {
		System.out.println("in get patient details "+patientId);

			//invoke service layer method
			
			return ResponseEntity.ok(patientService.getPatientDetails(patientId));
	}
	/*
	 * Desc - Get all patients by specific blood group - admin access
	i/p - blood group (path variable)
	o/p - List <DTO>
	Method - GET
	 */
	@GetMapping("/bloodgroup/{bloodGroup}")
	public ResponseEntity<?> getPatientsByBloodGroup(@PathVariable BloodGroup bloodGroup)
	{
		System.out.println("in get patients by bg "+bloodGroup);
		return ResponseEntity.ok(patientService.getPatientsByBloodGroup(bloodGroup));
	}
	


}
