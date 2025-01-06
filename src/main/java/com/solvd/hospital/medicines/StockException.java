package com.solvd.hospital.medicines;

// Simple unchecked exception for Medicine-related issues
public class StockException extends RuntimeException {

    // Constructor accepting a custom message
    public StockException(String message) {
        super(message);  // Pass the message to the RuntimeException superclass
    }
}
