package com.solvd.hospital.hospital.structure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pulmonology extends Branch {

    private static final Logger log = LogManager.getLogger(Pulmonology.class);

    public Pulmonology() {
        this.name = "Pulmonology";
        this.maxCapacity = 20;
    }

    @Override
    public void setMaxCapacity(int capacity) {
        super.setMaxCapacity(capacity);
        log.info("New max capacity for {}: {}", name, capacity);
    }
}