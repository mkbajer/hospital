package com.solvd.hospital.devices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class DeviceArchive implements AutoCloseable {

    private static final Logger log = LogManager.getLogger(DeviceArchive.class);
    private final BufferedWriter writer;

    // Constructor to initialize the BufferedWriter for the specified file
    public DeviceArchive(String filePath) throws IOException {
        writer = new BufferedWriter(new FileWriter(filePath, true));
    }

    // Method to save a list of devices to the file
    public void saveDevices(Set<Device> devices) throws IOException, SerialException {
        Set<String> serials = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\devices.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Serial Number: ")) {
                    String existingSN = line.substring(15);
                    serials.add(existingSN);
                }
            }
        }
        for (Device device : devices) {
            String serial = device.getSerialNumber();
            if (serials.contains(serial)) {
                log.info("Device already in file , serialNO : {}", serial);
                continue;
            }
            writer.write("Device Name: " + device.getName());
            writer.newLine();
            writer.write("Serial Number: " + device.getSerialNumber());
            writer.newLine();
            writer.write("------------");
            writer.newLine();
            serials.add(serial);
        }
        log.info("Devices saved successfully.");
    }

    @Override
    public void close() throws IOException {
        try {
            writer.close();
            log.info("DeviceSaver closed successfully.");
        } catch (IOException e) {
            log.error("Error closing DeviceSaver: {}", e.getMessage());
        }
    }
}