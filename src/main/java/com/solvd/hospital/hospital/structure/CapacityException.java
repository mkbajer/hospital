package com.solvd.hospital.hospital.structure;

public class CapacityException extends RuntimeException {

    // Constructor accepting a custom message
    public CapacityException(String message) {
        super(message);  // Pass the message to the RuntimeException constructor
    }
}
