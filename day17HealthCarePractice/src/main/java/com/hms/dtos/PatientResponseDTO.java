package com.hms.dtos;

import com.hms.entities.BloodGroup;
import com.hms.entities.Gender;

public record PatientResponseDTO(BloodGroup bloodGroup, Gender gender, String familyHistory) {
}
