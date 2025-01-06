package com.solvd.hospital.employees;

import com.solvd.hospital.basicInformation.Hierarchy;
import com.solvd.hospital.medicines.Medicine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class Doctor extends Employee {

    private static final Logger log = LogManager.getLogger(Doctor.class);

    public Doctor(String name, String surname, String position, Hierarchy hierarchy) {
        super(name, surname, position, hierarchy);
    }

    @Override
    public void setPayout(BigDecimal hourly) {
        BigDecimal adjustedPayout = hourly.multiply(BigDecimal.valueOf(1.78));
        log.info("employees.Doctor's payout rate adjusted to: {}", adjustedPayout);
    }

    // Polymorphic method to prescribe medicine
    public void prescribeMedicine(Medicine medicine) {
        log.info("employees.Doctor {} {} is prescribing...", getName(), getSurname());
        log.info(medicine.getMedicineDetails());
    }

    // Polymorphic method to administer medicine
    public void administerMedicine(Medicine medicine) {
        log.info("employees.Doctor {} {} is administering...", getName(), getSurname());
        log.info(medicine.administer());
    }

    @Override
    public void displayInfo() {
        log.info("Name: {}", name);
        log.info("Surname: {}", surname);
        log.info("Position: {}", position);
        log.info("Hierarchy: {}", hierarchy);
        if (address != null) {
            log.info("basicInformation.Address: {}", address);
        }
        if (hourly != null) {
            log.info("Hourly Rate: {}", hourly);
        }
    }
}