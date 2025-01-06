package com.solvd.hospital.hospital.structure;

import com.solvd.hospital.employees.Employee;
import com.solvd.hospital.employees.OfficeWorker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class Finance extends Department {

    private static final Logger log = LogManager.getLogger(Finance.class);
    private LocalDate date;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public void addEmployee(Employee employee) {
        if (canRegister()) {
            if (employee instanceof OfficeWorker) {
                employees.add(employee);
            } else {
                log.info("Only OfficeWorkers can be added to Finance.");
            }
            log.info("Employee added to Finance");
        } else {
            log.info(" ! Maximum capacity reached");
        }
    }

    @Override
    public boolean canRegister() {

        return employees.size() < getMaxCapacity();
    }

    @Override
    public int getMaxCapacity() {
        return 4;
    }
}