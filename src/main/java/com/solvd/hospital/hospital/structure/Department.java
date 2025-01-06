package com.solvd.hospital.hospital.structure;

import com.solvd.hospital.employees.Employee;
import com.solvd.hospital.interfaces.CapacityManager;
import com.solvd.hospital.interfaces.Registrable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public abstract class Department implements CapacityManager, Registrable {

    private static final Logger log = LogManager.getLogger(Department.class);
    protected List<Employee> employees = new ArrayList<>();

    public static Function<List<Employee>, List<String>> getEmployeePosition() {
        return empList -> empList.stream()
                .map(Employee::getPosition)
                .collect(Collectors.toList());
    }

    public abstract void addEmployee(Employee employee);

    public void showEmployees() {
        if (employees != null) {
            log.info("Employees in department: ");
            for (Employee employee : employees) log.info("- {}", employee.getName());
        } else {
            log.info("No employee assigned to this department.");
        }
    }
}