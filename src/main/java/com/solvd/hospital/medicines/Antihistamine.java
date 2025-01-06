package com.solvd.hospital.medicines;

import java.time.LocalDateTime;

public final class Antihistamine extends Medicine {

    private final String prescription;
    private final LocalDateTime dateOfFinalUse;

    public Antihistamine(String name, double dose, String prescription, LocalDateTime dateOfFinalUse) {
        super(name, dose);
        this.prescription = prescription;
        this.dateOfFinalUse = dateOfFinalUse;
    }

    public String getPrescription() {
        return prescription;
    }

    public LocalDateTime getDateOfFinalUse() {
        return dateOfFinalUse;
    }

    @Override
    public String getMedicineDetails() {
        return "medicines.Antihistamine: " + getName() + ". Prescription: " + prescription + ". Use before: " + dateOfFinalUse;
    }

    @Override
    public String administer() {
        return "Administering antihistamine: " + getName() + ". Follow prescription carefully.";
    }
}
