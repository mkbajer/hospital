package com.solvd.hospital.basicInformation;

import com.solvd.hospital.hospital.structure.Neurology;
import com.solvd.hospital.hospital.structure.Pulmonology;
import com.solvd.hospital.hospital.structure.Reception;
import com.solvd.hospital.patients.Patient;
import com.solvd.hospital.patients.PatientNotFoundException;
import com.solvd.hospital.patients.PatientOptional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsReflectionOptional {

    private static final Logger LOGGER = LogManager.getLogger(StreamsReflectionOptional.class);

    public static void main(String[] args) {

        Reception reception = new Reception();
        Neurology neurology = new Neurology();
        Pulmonology pulmonology = new Pulmonology();
        Patient one = new Patient("Alex", "Dog", 17);
        Patient two = new Patient("Matt", "Hamster", 67);
        Patient three = new Patient("John", "Bird", 28);
        Patient four = new Patient("John", "Cat", 16);
        Patient five = new Patient("Andre", "Hamster", 18);
        Patient six = new Patient("Kate", "Bush", 30);
        Patient seven = new Patient("Margaret", "Cloud", 42);
        Patient eight = new Patient("Monica", "Cat", 35);
        Patient nine = new Patient("Pamela", "Snow", 30);
        Patient ten = new Patient("Susan", "Sarandon", 61);
        reception.register(one, neurology);
        reception.register(two, neurology);
        reception.register(three, neurology);
        reception.register(four, neurology);
        reception.register(five, neurology);
        reception.register(six, neurology);
        reception.register(seven, neurology);
        reception.register(eight, neurology);
        reception.register(nine, neurology);
        reception.register(ten, neurology);


        List<String> names = List.of("Jack", "Jones", "Agnes", "Inez", "Dave", "Clark", "Anthony", "Gertruda", "Sophie",
                "Cleo", "Damian", "Agnes");

        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("A") || name.startsWith("D"))
                .toList();
        LOGGER.info("Filtered names: {}", filteredNames);

        List<String> upperCaseNames = names.stream()
                .filter(name -> name.contains("o"))
                .map(String::toUpperCase)
                .toList();
        LOGGER.info("Uppercase names containing 'o': {}", upperCaseNames);

        names.stream()
                .filter(name -> name.endsWith("a") || name.contains("hie"))
                .flatMap(str -> Stream.of(str.charAt(0)))
                .forEach(LOGGER::info);

        List<String> lowerCaseNamesPeek = names.stream()
                .filter(name -> name.contains("z"))
                .peek(LOGGER::debug)
                .map(String::toLowerCase)
                .toList();
        LOGGER.info("Lowercase names containing 'z': {}", lowerCaseNamesPeek);

        var count = names.stream()
                .peek(LOGGER::debug)
                .filter(name -> name.startsWith("A"))
                .count();
        LOGGER.debug("Count of names starting with 'A': {}", count);

        List<String> olderPatients = neurology.getPatients().stream()
                .filter(patient -> patient.getAge() > 60)
                .map(Patient::getName)
                .toList();
        LOGGER.info("Patients older than 60: {}", olderPatients);

        boolean surnameWithC = neurology.getPatients().stream()
                .peek(p -> LOGGER.debug("Check: {}", p.getSurname()))
                .anyMatch(patient -> patient.getSurname().startsWith("C"));
        LOGGER.info("Patient with surname starting from 'C': {}", surnameWithC);

        boolean allAdults = neurology.getPatients().stream()
                .allMatch(patient -> patient.getAge() >= 18);
        LOGGER.info("Are all patients adults? {}", allAdults);

        Optional<Patient> firstOlderThan50 = neurology.getPatients().stream()
                .filter(patient -> patient.getAge() > 50)
                .findFirst();
        firstOlderThan50.ifPresent(patient ->
                LOGGER.info("First patient older than 50: {}", patient.getName())
        );

        Map<Boolean, List<Patient>> partitionedByNameLength = neurology.getPatients().stream()
                .peek(patient -> LOGGER.debug("Checking name: {}", patient.getName()))
                .collect(Collectors.partitioningBy(patient -> patient.getName().length() > 5));
        LOGGER.info("Patients divided by name length (> 5) : {}", partitionedByNameLength.get(true));

        try {
            Optional<Patient> result = PatientOptional.findPatientBySurname(pulmonology, "Smiths");
            Patient patient = result.orElseThrow(() -> new PatientNotFoundException("Not found!"));
            LOGGER.info("Patient Found: {}", patient.getName());
        } catch (PatientNotFoundException e) {
            LOGGER.error(e.getMessage());
        }

        try {

            Class<?> employeeClass = Class.forName("com.solvd.hospital.employees.Doctor");

            LOGGER.info("Fields:");
            Arrays.stream(employeeClass.getDeclaredFields())
                    .forEach(field -> LOGGER.info("Name {} , Type: {} , Modifiers {}",
                            field.getName(), field.getType(), Modifier.toString(field.getModifiers())));

            LOGGER.info("Constructors:");
            Arrays.stream(employeeClass.getConstructors())
                    .forEach(constructor -> LOGGER.info("Constructor: {} , Parameters: {}",
                            constructor.getName(), Arrays.toString(constructor.getParameterTypes())));

            LOGGER.info("Methods:");
            Arrays.stream(employeeClass.getMethods())
                    .forEach(method -> LOGGER.info("Name: {} , Return Type: {} , Parameters: {} , Modifiers: {}",
                            method.getName(), method.getReturnType(),
                            Arrays.toString(method.getParameterTypes()),
                            Modifier.toString(method.getModifiers())));

            Constructor<?> constructor = employeeClass.getConstructor(
                    String.class, String.class, String.class, Hierarchy.class);

            Object employee = constructor.newInstance("Tester", "Tested", "Tester", Hierarchy.LEADER);

            Method displayInfo = employeeClass.getMethod("displayInfo");
            displayInfo.invoke(employee);

        } catch (Exception e) {
            LOGGER.error("Reflection failed: ", e);
        }
    }
}
