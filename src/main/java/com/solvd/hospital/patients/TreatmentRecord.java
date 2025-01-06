package com.solvd.hospital.patients;

import com.solvd.hospital.employees.Doctor;
import com.solvd.hospital.medicines.Medicine;

import java.time.LocalDateTime;
import java.util.Objects;

public class TreatmentRecord {
    private LocalDateTime day;
    private Medicine medicine;
    private Doctor doctor;
    private String notes;

    public TreatmentRecord(LocalDateTime day, Medicine medicine, Doctor doctor, String notes) {
        this.day = day;
        this.medicine = medicine;
        this.doctor = doctor;
        this.notes = notes;
    }

    public LocalDateTime getDay() {
        return day;
    }

    public void setDay(LocalDateTime day) {
        this.day = day;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreatmentRecord that = (TreatmentRecord) o;

        return Objects.equals(day, that.day) &&
                Objects.equals(medicine, that.medicine) &&
                Objects.equals(doctor, that.doctor) &&
                Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, medicine, doctor, notes);
    }
}
