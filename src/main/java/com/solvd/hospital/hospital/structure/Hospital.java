package com.solvd.hospital.hospital.structure;

import com.solvd.hospital.basicInformation.Address;
import com.solvd.hospital.devices.SerialException;
import com.solvd.hospital.interfaces.SerializableNo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public final class Hospital {

    private static final Logger log = LogManager.getLogger(Hospital.class);
    public static int currentPatients; // Static variable - shared among all instances

    static {
        currentPatients = 0; // initializing before class is made and called
    }

    private String name;
    private List<Department> departments = new ArrayList<>();
    private List<Branch> branches = new ArrayList<>();
    private Address address;

    public static void CurrentVacancy() {

        log.info("Current patients: {}", currentPatients);
    }

    // Method that accepts any object implementing SerializableNo
    public static void displaySerialInfo(SerializableNo item) {
        try {
            log.info("Serial Number: {}", item.getSerialNumber());
        } catch (SerialException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Department> getDepartments() throws HospitalException {
        if (departments == null) {
            throw new HospitalException("Departments have not been initialized");
        }
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Branch> getBranches() throws HospitalException {
        if (branches == null) {
            throw new HospitalException("Branches have not been initialized");
        }
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}