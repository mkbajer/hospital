package com.solvd.hospital.interfaces;

import com.solvd.hospital.devices.SerialException;

import java.io.Serializable;

public interface SerializableNo extends Serializable {
    String getSerialNumber() throws SerialException;
}
