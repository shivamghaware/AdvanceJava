package com.healthcare.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healthcare.dtos.AppointmentResp;
import com.healthcare.entities.Appointment;
import com.healthcare.entities.Status;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
	//custom query - select @Query - JPQL const expression - DTO
	@Query("select new com.healthcare.dtos.AppointmentResp(a.id,a.startDateTime,u.firstName,u.lastName) from Appointment a join a.myDoctor.userDetails u  where a.myPatient.id=:pid and a.status=:sts")
	List<AppointmentResp> getPatientAppointments(@Param("pid") Long patientId,@Param("sts") Status status);

}
