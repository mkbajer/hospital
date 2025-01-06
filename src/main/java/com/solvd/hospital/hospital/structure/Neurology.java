package com.solvd.hospital.hospital.structure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Neurology extends Branch {

    private static final Logger log = LogManager.getLogger(Neurology.class);

    public Neurology() {
        this.name = "Neurology";
        this.maxCapacity = 30;
    }

    @Override
    public void setMaxCapacity(int capacity) {
        super.setMaxCapacity(capacity);
        log.info("New max capacity for {}: {}", name, capacity);
    }
}