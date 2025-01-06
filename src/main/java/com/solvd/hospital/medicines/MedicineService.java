package com.solvd.hospital.medicines;

public class MedicineService {

    public static StringBuilder usageType(Medicine medicine) {
        StringBuilder result = new StringBuilder();
        result.append(medicine.getName());
        double dose = medicine.getDose();
        double threshold = 100.0;
        boolean b = dose >= threshold;

        if (medicine instanceof Antibiotic antibiotic) {
            result.append(" Please check the dose before applying ");
            if (b) {
                result.append("dose! -> ");
                result.append(antibiotic.getDose());
            }
        } else if (medicine instanceof Antihistamine antihistamine) {
            result.append(" Please check date of use for dose greater than 100 ");
            if (b) {
                result.append(antihistamine.getMedicineDetails());
            }
        } else if (medicine instanceof Medicine) {
            result.append(" This needs no prescription ");
            result.append(medicine.getMedicineDetails());
            if (b) {
                result.append(medicine.getStock());
            }
        }
        return result;
    }
}