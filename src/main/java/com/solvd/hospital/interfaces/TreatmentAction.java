package com.solvd.hospital.interfaces;

import com.solvd.hospital.patients.Patient;
import com.solvd.hospital.patients.TreatmentRecord;

@FunctionalInterface
public interface TreatmentAction {
    void apply(Patient patient, TreatmentRecord record);
}
