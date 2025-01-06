package com.solvd.hospital.devices;

import com.solvd.hospital.interfaces.SerializableNo;

import java.util.Objects;

public class Device implements SerializableNo {

    private static int counter = 0;
    private final String serialNo;
    private final String name;

    public Device(String name) {
        this.name = name;
        counter++;
        this.serialNo = "DS" + String.format("%05d", counter);
    }

    @Override
    public String getSerialNumber() throws SerialException {
        if (serialNo == null || serialNo.isEmpty()) {
            throw new SerialException("Serial number is missing for this device.");
        }
        return serialNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Device device)) return false;
        return Objects.equals(serialNo, device.serialNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNo);
    }

    public String getName() {
        return name;
    }
}

