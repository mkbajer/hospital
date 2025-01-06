package com.solvd.hospital.patients;

import com.solvd.hospital.hospital.structure.Branch;

import java.util.Optional;

public class PatientOptional {
    public static Optional<Patient> findPatientBySurname(Branch branch, String surname) {

        Patient matchedPatient = branch.getPatients().stream()
                .filter(patient -> patient.getSurname().equalsIgnoreCase(surname))
                .findFirst()
                .orElse(null);

        return Optional.ofNullable(matchedPatient);
    }
}