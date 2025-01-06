package com.solvd.hospital.employees;

import com.solvd.hospital.basicInformation.Address;
import com.solvd.hospital.basicInformation.Hierarchy;
import com.solvd.hospital.interfaces.Display;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public abstract class Employee implements Display {

    private static final Logger log = LogManager.getLogger(Employee.class);
    protected String name;
    protected String surname;
    protected Address address;
    protected String position;
    protected BigDecimal hourly;
    protected Hierarchy hierarchy;

    public Employee(String name, String surname, String position, Hierarchy hierarchy) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.hierarchy = hierarchy;

    }

    @Override
    public void displayInfo() {
        log.info("{} {}", getName(), getSurname());
        log.info(getPosition());
        log.info(getHierarchy());
    }

    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BigDecimal getPayout() {
        return hourly;
    }

    public void setPayout(BigDecimal hourly) {
        this.hourly = hourly;
    }
}
