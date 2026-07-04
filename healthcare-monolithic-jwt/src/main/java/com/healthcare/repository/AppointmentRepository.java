package com.healthcare.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healthcare.dtos.AppointmentDTO;
import com.healthcare.dtos.CompleteAppointmentDetails;
import com.healthcare.entities.Appointment;
import com.healthcare.entities.Status;
/*
 * private Long appointmentId;
	private LocalDateTime appointmentStart;
	private String firstName;
	private String lastName;
 */
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
//custom query - DTO projection
	@Query("select new com.healthcare.dtos.AppointmentDTO(a.id,a.startDateTime,u.firstName,u.lastName) from Appointment a join a.myDoctor.userDetails u where a.myPatient.id=:pid and a.status=:sts")
	List<AppointmentDTO> getAppointmentDetailsForPatient(@Param("pid")Long pid1,@Param("sts")Status sts1);
	
	/*
	 * String jpql = """
				   select a.id from Appointment a where
				   a.myDoctor.id =:docId and
				   a.status = :sts and
				   a.startDateTime < :newEnd and
				   a.endDateTime > :newStart
				""";
				- is doc available
	 */
	boolean existsByMyDoctorIdAndStatusAndStartDateTimeBeforeAndEndDateTimeAfter
	(Long docId,Status sts,LocalDateTime end, LocalDateTime start);
	//is patient available
	boolean existsByMyPatientIdAndStatusAndStartDateTimeBeforeAndEndDateTimeAfter
	(Long patientId,Status sts,LocalDateTime end, LocalDateTime start);

	/*
	 * Custom query to get complete appointment details
	 */
	@Query("""		
		select new com.healthcare.dtos.CompleteAppointmentDetails
		(a.id,a.startDateTime,a.endDateTime,a.status,d.firstName,d.lastName,p.firstName,p.lastName) 
		from Appointment a join a.myPatient.userDetails p
		join a.myDoctor.userDetails d
			""")
	List<CompleteAppointmentDetails> getAllAppointments();

}
