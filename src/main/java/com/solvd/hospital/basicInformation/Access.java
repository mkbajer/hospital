package com.solvd.hospital.basicInformation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Access {

    HIGH("High level access", "Only Management", true),
    MEDIUM("Medium level access", "For health care workers", true),
    LOW("Low level access", "Do not use", false);

    private static final Logger log = LogManager.getLogger(Access.class);
    final String displayName;
    final String description;
    final boolean active;

    Access(String displayName, String description, boolean active) {
        this.displayName = displayName;
        this.description = description;
        this.active = active;
    }

    public static boolean isAccessPermitted(Access access) {
        return access.isActive();
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public void displayAccessInfo() {
        log.info("Access Level: " + displayName);
        log.info("Description: " + description);
        log.info("Active: " + (active ? "Yes" : "No"));
    }
}