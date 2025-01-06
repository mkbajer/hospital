package com.solvd.hospital.patients;

import com.solvd.hospital.interfaces.Curable;
import com.solvd.hospital.interfaces.TreatmentAction;
import com.solvd.hospital.medicines.Medicine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Patient implements Curable {

    private static final Logger log = LogManager.getLogger(Patient.class);
    private final String name;
    private final Map<LocalDateTime, TreatmentRecord> treatmentHistory = new HashMap<>();
    private String surname;
    private int age;
    private Medicine medicine;

    public Patient(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Patient(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Patient() {
        this.name = "Gal";
        this.surname = "Anonim";
    }

    @Override
    public String toString() {
        return name + " " + surname + " (Age: " + age + ")";
    }

    public void addTreatment(LocalDateTime day, TreatmentAction action) {
        TreatmentRecord record = new TreatmentRecord(day, null, null, "Initial treatment");
        action.apply(this, record);
        treatmentHistory.put(day, record);
    }

    public void addTreatment(LocalDateTime day, String notes) {
        addTreatment(day, (p, r) -> r.setNotes(notes));
    }

    public void addTreatment(LocalDateTime day, Medicine medicine) {
        addTreatment(day, (p, r) -> r.setMedicine(medicine));
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @Override
    public void treatmentProgram() {
        log.info("Generating a dynamic treatment plan for: {}", name);
    }

    @Override
    public void medicineSlot() {
        log.info("Scheduling medicine slots for {}", name);
    }
}
