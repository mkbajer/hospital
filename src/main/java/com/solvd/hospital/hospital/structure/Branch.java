package com.solvd.hospital.hospital.structure;

import com.solvd.hospital.employees.Doctor;
import com.solvd.hospital.interfaces.CapacityManager;
import com.solvd.hospital.interfaces.Registrable;
import com.solvd.hospital.medicines.Medicine;
import com.solvd.hospital.patients.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public abstract class Branch implements CapacityManager, Registrable {

    private static final Logger log = LogManager.getLogger(Branch.class);
    protected String name;
    protected List<Doctor> doctors = new ArrayList<>();
    protected int currentCapacity = 0;
    protected int maxCapacity;
    protected List<Patient> patients = new ArrayList<>();
    protected Map<String, String> medicines = new HashMap<>();

    public List<Patient> getPatients() {
        return patients;
    }

    @Override
    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    public void setMaxCapacity(int capacity) {
        if (capacity < currentCapacity)
            throw new CapacityException("The maximum capacity is reached !!! ");
        this.maxCapacity = capacity;
    }

    @Override
    public boolean canRegister() {
        return currentCapacity < maxCapacity;
    }

    public void addPatient(Patient patient) {
        if (!canRegister()) {
            throw new CapacityException(" Cannot add more patients, please check maximum Branch capacity.");
        }
        patients.add(patient);
        patients.set(currentCapacity, patient);
        currentCapacity++; // counts beds which are used in a branch
        Hospital.currentPatients++; // counts all patients in hospital
    }

    public void showPatients() {
        if (currentCapacity == 0) {
            log.info("No patients in {}", name);
        } else {
            IntStream.range(0, currentCapacity)
                    .forEach(i -> log.info("{} - {}", i + 1, patients.get(i).getName()));
        }
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public void addMedicine(Medicine medicine) {
        String serialNo = medicine.getSerialNumber();
        String name = medicine.getName();
        if (medicines.containsKey(serialNo)) {
            log.info("Medicine with serial number {} already exists.", serialNo);
        } else {
            medicines.put(serialNo, name);
            log.info("Medicine added: {} (Serial: {})", name, serialNo);
        }
    }

    public void showMedicines() {
        if (medicines.isEmpty()) {
            log.info("No medicines available in {}", name);
        } else {
            log.info("Medicines in {}:", name);
            medicines.forEach((key, value) ->
                    log.info("Serial Number: {}, Name: {}", key, value));
        }
    }
}