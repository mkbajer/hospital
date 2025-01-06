package com.solvd.hospital.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Display {

    Logger log = LogManager.getLogger(Display.class);

    void displayInfo();

    default void show() {
        log.info(String.valueOf(hashCode()));
    }
}


