package com.solvd.hospital.patients;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public final class MedicalHistory {

    private static final String DIRECTORY_PATH = "D:\\tools\\intelij-workspace\\hospital\\src\\main\\resources";
    private static final Logger log = LogManager.getLogger(MedicalHistory.class);
    private final File file;

    // Constructor that initializes the file object and creates the file if it doesn't exist
    public MedicalHistory(String fileName) {
        file = new File(DIRECTORY_PATH, fileName);
        try {
            if (file.createNewFile()) {
                log.info("File created: {}", fileName);
            } else {
                log.info("File already exists: {}", file.getAbsolutePath());
            }
        } catch (IOException e) {
            log.error("An error occurred while creating the file {}.", fileName);
        }
    }

    public void writeMedicalHistory(Patient patient, String history) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("Patient: " + patient.getName() + " " + patient.getSurname());
            writer.newLine();
            writer.write("History: " + history);
            writer.newLine();
            writer.write("------------");
            writer.newLine();
            log.info("Medical history written for {}", patient.getName());
            log.info(" ");
        } catch (FileNotFoundException e) {
            log.error("Error writing medical history: {}", e.getMessage());
            log.info(" ");
        }
    }

    /*public void readMedicalHistory1() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                log.info(line);
            }
        } catch (FileNotFoundException e) {
            log.error("Error reading medical history: {}", e.getMessage());
        }
    }*/

    public void readMedicalHistory() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines().forEach(log::info); // Stream lines directly and log each
        } catch (FileNotFoundException e) {
            log.error("Error reading medical history: {}", e.getMessage());
        }
    }

    public File getFile() {
        return file;
    }
}
