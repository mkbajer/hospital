package com.solvd.hospital.employees;

import com.solvd.hospital.basicInformation.Hierarchy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OfficeWorker extends Employee {

    private static final Logger log = LogManager.getLogger(OfficeWorker.class);

    public OfficeWorker(String name, String surname, String position, Hierarchy hierarchy) {
        super(name, surname, position, hierarchy);
    }

    @Override
    public void displayInfo() {
        log.info("Name: {}", name);
        log.info("Surname: {}", surname);
        log.info("Position: {}", position);
        log.info("Hierarch {}", hierarchy);
    }

}


