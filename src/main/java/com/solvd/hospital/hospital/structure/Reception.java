package com.solvd.hospital.hospital.structure;

import com.solvd.hospital.employees.Employee;
import com.solvd.hospital.employees.OfficeWorker;
import com.solvd.hospital.patients.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class Reception extends Department {

    private static final Logger log = LogManager.getLogger(Reception.class);
    private LocalDate date;

    @Override
    public void addEmployee(Employee employee) {

        if (canRegister()) {
            if (employee instanceof OfficeWorker) {
                employees.add(employee);
                log.info("Only OfficeWorkers can be added to Reception.");
            }
            log.info("Employee added to Reception");
        } else {
            log.info(" ! Max capacity reached");
        }
    }

    public void setDate(LocalDate date) {
        this.date = LocalDate.now();
    }

    public void showPatient(Branch branch) {
        log.info("Patients in {}:", branch.name);
        branch.showPatients();
    }

    public String register(Patient patient, Branch branch) {
        if (branch.canRegister()) {
            branch.addPatient(patient);
            setDate(date);
            return "Patient " + patient.getName() + " registered successfully at " + date + " in " + branch.name;
        } else {
            return "Patient " + patient.getName() + " Registration on " + date + " failed: " + branch.name.toUpperCase() + " is at full capacity.";
        }
    }

    @Override
    public boolean canRegister() {

        return employees.size() < getMaxCapacity();
    }

    @Override
    public int getMaxCapacity() {
        return 3;
    }
}