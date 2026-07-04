package com.healthcare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.service.PatientService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@Validated
public class PatientController {
	private final PatientService patientService;
	
 /*
	 * Design API end point in Patient controller
	 * Desc -Get Patient Specific Details
      URI -/patients/{patientId}
      Method - GET 
      path variable(URI template var) 	 
*  Response - ResponseEntity<?>
	 * error resp - SC 404 (in case of invalid user id) 
	 * + Api resp (DTO) - err mesg
	 * success - SC 200 + PatientDTO
	 */
	@GetMapping("/{patientId}")
	public ResponseEntity<?> getPatientDetails(@PathVariable @Min(5) @Max(8)
			Long patientId) {
		System.out.println("in get patient "+patientId);
	//	try {
			//invoke service  method
			return ResponseEntity.ok(patientService.getPatientDetails(patientId));
//		} catch (RuntimeException e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND)
//					.body(new ApiResponse(e.getMessage(), "Failed"));
//		}
	}


}
