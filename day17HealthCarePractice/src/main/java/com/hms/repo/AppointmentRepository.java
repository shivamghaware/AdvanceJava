package com.hms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hms.dtos.AppointmentResponseDTO;
import com.hms.entities.Status;
import com.hms.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
//	@Query("select new com.hms.dtos.AppointmentResponseDTO(a.id,a.startDateTime,u.firstName,u.lastName) from Appointment a join a.myDoctor.userDetails u  where a.myPatient.id=:pid and a.status=:sts")
//	List<AppointmentResponseDTO> getPatientAppointments(@Param("pid") Long patientId,@Param("sts") Status status);
	
	List<AppointmentResponseDTO> findByMyPatientIdAndStatus(Long patientId, Status status);
}
