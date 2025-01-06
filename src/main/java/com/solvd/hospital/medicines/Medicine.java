package com.solvd.hospital.medicines;

import com.solvd.hospital.interfaces.SerializableNo;

import java.util.Objects;

public class Medicine implements SerializableNo {

    private static int counter = 0;
    private final String serialNo;
    private final String name;
    private final double dose;
    private Integer stock;

    public Medicine(String name, double dose) {
        this.name = name;
        this.dose = dose;
        counter++;
        this.serialNo = "MS" + String.format("%05d", counter);
    }

    @Override
    public String getSerialNumber() {
        return serialNo;

    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        if (stock < 0) {
            throw new StockException("Stock cannot be negative.");
        }
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getDose() {
        return dose;
    }

    public String getMedicineDetails() {
        return "General medicine: " + name + ". Dose: " + dose + "mg ";
    }

    public String administer() {
        return "Administering general medicine: " + name;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", dose=" + dose +
                ", stock=" + stock +
                ", serial no=" + serialNo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicine medicine)) return false;
        return Double.compare(dose, medicine.dose) == 0 && Objects.equals(name, medicine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dose);
    }
}
