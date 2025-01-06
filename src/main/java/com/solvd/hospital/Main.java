package com.solvd.hospital;

import com.solvd.hospital.basicInformation.*;
import com.solvd.hospital.devices.Device;
import com.solvd.hospital.devices.DeviceArchive;
import com.solvd.hospital.devices.FindItem;
import com.solvd.hospital.devices.SerialException;
import com.solvd.hospital.employees.Doctor;
import com.solvd.hospital.employees.Employee;
import com.solvd.hospital.employees.OfficeWorker;
import com.solvd.hospital.hospital.structure.*;
import com.solvd.hospital.medicines.Antibiotic;
import com.solvd.hospital.medicines.Antihistamine;
import com.solvd.hospital.medicines.Medicine;
import com.solvd.hospital.medicines.MedicineService;
import com.solvd.hospital.patients.MedicalHistory;
import com.solvd.hospital.patients.Patient;
import com.solvd.hospital.patients.PatientNotFoundException;
import com.solvd.hospital.patients.PatientOptional;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*

TODO list :
MedicineService - 'if' statement can be replaced with 'switch' statement
Patient - Variable 'timeSlots' is never used
Reception - Parameter 'date' is never used
DeviceArchive - Exception 'java.io.IOException' is never thrown in the method
(optional) Create custom LinkedList class with generic. (this class must implement the List interface)
try to create any branches (using console commands), add any code there and open a pull request to the main branch
try to simulate conflicts and resolve them with the help of - merge - rebase
Create 2 Threads using Runnable and Thread.
Create Connection Pool: create a class named Connection which has any methods
(these methods can just return any mock (hardcoded) data, like public String getName() {return "John";}).
Initialize pool with 5 sizes. Load Connection Pool using threads and Thread Pool(7 threads).
5 threads should be able to get the connection. 2 Threads should wait for the next available connection.
The program should wait as well.
Create some threads using CompletableFuture - just in main method

release your connection inside the "finally" block
replace e.printStackTrace(); with LOGGER.error
rename "private static final Logger log" to "private static final Logger LOGGER"
wait at least in one place of your completed future (using method named join())

you repository is empty, you need to "upload" you project there
you need to play around with branches creating, pull requests and conflict resolving (using merge and rebase)

 */

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws AddressException, IOException {

        Reception reception = new Reception();
        Finance finance = new Finance();
        Pulmonology pulmonology = new Pulmonology();
        Neurology neurology = new Neurology();
        Patient patient1 = new Patient("Mat", "Cat");
        Patient patient2 = new Patient("John", "Wick");
        Patient patient3 = new Patient();
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


        Address hospitalAddress = new Address("Jagiellonska", 123, "Warszawa", "00-230");
        Hospital hospital = new Hospital();
        hospital.setName("St.Joseph");
        hospital.setDepartments(List.of(reception));
        hospital.setDepartments(List.of(finance));
        hospital.setBranches(List.of(pulmonology, neurology));
        pulmonology.setMaxCapacity(2);
        hospital.setAddress(hospitalAddress);
        Hospital hospitalTest = new Hospital();
        try {
            hospitalTest.getBranches();
        } catch (HospitalException e) {
            log.error("Exception caught: {}", e.getMessage());
        }

        try {
            log.info(String.valueOf((hospital.getBranches())));
        } catch (HospitalException e) {
            throw new RuntimeException(e);
        } finally {
            log.info("Branches should be declared following the Branch rules");
        }

        reception.register(patient1, neurology);
        reception.register(patient2, pulmonology);
        reception.register(patient3, pulmonology);
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

        log.info(reception.register(patient2, neurology));
        log.info(reception.register(patient3, pulmonology));
        reception.showPatient(pulmonology);
        reception.showPatient(neurology);
        log.info(hospitalAddress);
        try {
            pulmonology.addPatient(new Patient("Emily", "Brown"));

        } catch (CapacityException e) {
            log.error("Branch exception caught! ->{}", e.getMessage());
        }

        OfficeWorker receptionist = new OfficeWorker("Ann", "Lee", "receptionist", Hierarchy.JUNIOR);
        OfficeWorker receptionist2 = new OfficeWorker("Jack", "Two", "receptionist", Hierarchy.MID);
        OfficeWorker financist = new OfficeWorker("Kate", "Winslett", "financist", Hierarchy.LEADER);
        OfficeWorker financist2 = new OfficeWorker("X", "x", "r", Hierarchy.JUNIOR);
        Doctor firstd = new Doctor("John", "Smith", "neurologist", Hierarchy.LEADER);
        Doctor secondd = new Doctor("Adam", "Waltz", "pulmonologist", Hierarchy.LEADER);
        firstd.setPayout(BigDecimal.valueOf(34.5));
        financist.setPayout(BigDecimal.valueOf(34.6));

        reception.addEmployee(receptionist);
        reception.addEmployee(receptionist2);
        reception.showEmployees();
        finance.addEmployee(financist);
        finance.addEmployee(financist2);
        finance.showEmployees();
        financist.displayInfo();
        receptionist.displayInfo();
        Medicine painkiller = new Medicine("Paracetamol", 110.75);
        Medicine antibiotic = new Antibiotic("Amoxicillin", 250.75, "Take with food", LocalDateTime.now().plusDays(7));
        Medicine antihistamine = new Antihistamine("Cetirizine", 150.75, "Avoid alcohol", LocalDateTime.now().plusDays(14));
        firstd.prescribeMedicine(painkiller); // General medicine behavior
        firstd.prescribeMedicine(antibiotic); // medicines.Antibiotic-specific behavior
        firstd.prescribeMedicine(antihistamine); // medicines.Antihistamine-specific behavior
        // Administer medicines using polymorphism
        firstd.administerMedicine(painkiller); // General medicine behavior
        firstd.administerMedicine(antibiotic); // medicines.Antibiotic-specific behavior
        firstd.administerMedicine(antihistamine);
        log.info(String.valueOf(painkiller));
        log.info(String.valueOf(antibiotic));
        log.info("Are the medicines equal? {}", antibiotic.equals(painkiller)); // compare if antibiotic reference is equal to painkiller
        // - does it refer to the same object
        log.info("Painkiller HashCode: {}", painkiller.hashCode());
        log.info("medicines.Antibiotic HashCode: {}", antibiotic.hashCode());
        Hospital.CurrentVacancy();
        firstd.show();
        secondd.show();
        financist.show();
        log.info(String.valueOf(MedicineService.usageType(painkiller)));
        log.info(String.valueOf(MedicineService.usageType(antibiotic)));
        log.info(String.valueOf(MedicineService.usageType(antihistamine)));
        log.info("Displaying Medicine Serial Numbers:");
        Hospital.displaySerialInfo(antihistamine);
        Hospital.displaySerialInfo(antibiotic);

        Device device1 = new Device("Laptop");
        Device device2 = new Device("Printer");
        Device device3 = new Device("Monitor");
        Set<Device> devices = new HashSet<>();
        devices.add(device1);
        devices.add(device2);
        devices.add(device3);

        Consumer<Device> logDeviceInfo = device -> {
            try {
                log.info("Adding Device - Name: {}, Serial Number: {}",
                        device.getName(),
                        device.getSerialNumber());
            } catch (SerialException e) {
                throw new RuntimeException(e);
            }
        };
        devices.forEach(logDeviceInfo);

        Runnable task = () -> {
            try (DeviceArchive archive = new DeviceArchive("src\\main\\resources\\devices.txt")) {
                archive.saveDevices(devices);  // Save devices to the file
            } catch (IOException e) {
                log.error("Error saving devices: {}", e.getMessage());
            } catch (SerialException e) {
                throw new RuntimeException(e);
            }
        };
        new Thread(task).start();
        log.info("\nDisplaying Device Serial Numbers:");
        Hospital.displaySerialInfo(device1);
        Hospital.displaySerialInfo(device2);
        Hospital.displaySerialInfo(device3);
        patient1.treatmentProgram();
        patient2.medicineSlot();
        MedicalHistory patient1history = new MedicalHistory("patient1.txt");
        try {
            patient1history.writeMedicalHistory(patient1, "Dose of the medicine applied : 32");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            patient1history.readMedicalHistory();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        antibiotic.setStock(10);
        log.info("Stock set to: {}", antibiotic.getStock());

        pulmonology.addMedicine(painkiller);
        pulmonology.addMedicine(antibiotic);
        pulmonology.showMedicines();
        pulmonology.addMedicine(painkiller);

        // Attempt to set stock to a negative value (throws MedicineException)
        // painkiller.setStock(-5);

        // StringUtils and FileUtils
        File text = new File("src\\main\\resources\\new.txt");
        File results = new File("src\\main\\resources\\results.txt");
        String data = FileUtils.readFileToString(text, "UTF-8");
        String[] words = StringUtils.split(data, " \t\n\r.,;!?\"'()");
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            wordCounts.put(word.toLowerCase(), wordCounts.getOrDefault(word, 0) + 1);
        }
        FileUtils.write(results,
                StringUtils.join(wordCounts.entrySet().stream()
                        .map(e -> e.getKey() + ": " + e.getValue())
                        .toArray(String[]::new), "\n"),
                "UTF-8");

        log.debug("This is a DEBUG message.");
        log.info("This is an INFO message.");
        log.warn("This is a WARN message.");
        log.error("This is an ERROR message.");
        log.fatal("This is a FATAL message.");

        var clamp = Tools.CLAMP;
        Supplier<String> tool = () -> clamp.name();
        log.info(tool.get());

        Shift day = Shift.DAY;
        log.info("{}: {}", day.getDisplayName(), day.getHours());

        int currentHour = 15; // 3 pm
        log.info("Is {} within the {}? {}", currentHour, day.getDisplayName(), day.isTimeInShift(currentHour));

        Shift nextShift = day.getNextShift();
        log.info("The next shift after {} is {}", day.getDisplayName(), nextShift.getDisplayName());

        Access access = Access.HIGH;
        access.displayAccessInfo();
        Access.MEDIUM.displayAccessInfo();

        if (Access.isAccessPermitted(access)) {
            log.info("{} is permitted.", access.getDisplayName());
        } else {
            log.info("{} is NOT permitted.", access.getDisplayName());
        }

        List<Antibiotic> antibio = new ArrayList<>();
        antibio.add(new Antibiotic("Doxycycline", 100, "Take before meals", LocalDateTime.now().plusWeeks(2)));
        antibio.add(new Antibiotic("Amox", 50, "Take after meals", LocalDateTime.now().plusWeeks(5)));
        antibio.add(new Antibiotic("Flox", 25, "Take with meals", LocalDateTime.now().plusWeeks(1)));

        int days = 30;
        Predicate<Antibiotic> isNearExpiration = Antibiotic.isNearExpiration(days);
        antibio.stream()
                .filter(isNearExpiration)
                .map(Antibiotic::getMedicineDetails)
                .forEach(log::warn);

        List<Employee> employees = new ArrayList<>();
        employees.add(new OfficeWorker("Jack", "Daniels", "Financial Supervisor", Hierarchy.HEAD));
        employees.add(firstd);
        Department.getEmployeePosition()
                .apply(employees)
                .forEach(log::info);


        List<String> strings = new ArrayList<>();
        strings.add("app");
        strings.add("app");
        strings.add("app");
        strings.add("bios");
        strings.add("RAM");

        FindItem<String> stringFinder = new FindItem<>();
        int stringCount = stringFinder.search("app", strings);
        log.info("'app' appears {} time/es in the collection.", stringCount);

        List<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(2);
        integers.add(6);
        integers.add(3);

        FindItem<Integer> integerFinder = new FindItem<>();
        int integerCount = integerFinder.search(2, integers);
        log.info("'2' appears {} time/es in the list.", integerCount);

        FindItem<Device> deviceFinder = new FindItem<>();
        int deviceCount = deviceFinder.search(device1, devices);
        log.info("'Device1' found {} time/es in Devices", deviceCount);

        Patient treatment = new Patient("Test", "Treatment");
        treatment.addTreatment(LocalDateTime.of(2023, 12, 1, 10, 0), (p, r) -> {
            r.setMedicine(antihistamine);
            r.setDoctor(firstd);
            r.setNotes("Patient was hardly breathing");
        });

        treatment.addTreatment(LocalDateTime.now(), "Feeling better");

        List<String> names = List.of("Jack", "Jones", "Agnes", "Inez", "Dave", "Clark", "Anthony", "Gertruda", "Sophie",
                "Cleo", "Damian", "Agnes");

        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("A") || name.startsWith("D"))
                .toList();
        log.info("Filtered names: {}", filteredNames);

        List<String> upperCaseNames = names.stream()
                .filter(name -> name.contains("o"))
                .map(String::toUpperCase)
                .toList();
        log.info("Uppercase names containing 'o': {}", upperCaseNames);

        names.stream()
                .filter(name -> name.endsWith("a") || name.contains("hie"))
                .flatMap(str -> Stream.of(str.charAt(0)))
                .forEach(log::info);

        List<String> lowerCaseNamesPeek = names.stream()
                .filter(name -> name.contains("z"))
                .peek(log::debug)
                .map(String::toLowerCase)
                .toList();
        log.info("Lowercase names containing 'z': {}", lowerCaseNamesPeek);

        var count = names.stream()
                .peek(log::debug)
                .filter(name -> name.startsWith("A"))
                .count();
        log.debug("Count of names starting with 'A': {}", count);

        List<String> olderPatients = neurology.getPatients().stream()
                .filter(patient -> patient.getAge() > 60)
                .map(Patient::getName)
                .toList();
        log.info("Patients older than 60: {}", olderPatients);

        boolean surnameWithC = neurology.getPatients().stream()
                .peek(p -> log.debug("Check: {}", p.getSurname()))
                .anyMatch(patient -> patient.getSurname().startsWith("C"));
        log.info("Patient with surname starting from 'C': {}", surnameWithC);

        boolean allAdults = neurology.getPatients().stream()
                .allMatch(patient -> patient.getAge() >= 18);
        log.info("Are all patients adults? {}", allAdults);

        Optional<Patient> firstOlderThan50 = neurology.getPatients().stream()
                .filter(patient -> patient.getAge() > 50)
                .findFirst();
        firstOlderThan50.ifPresent(patient ->
                log.info("First patient older than 50: {}", patient.getName())
        );

        Map<Boolean, List<Patient>> partitionedByNameLength = neurology.getPatients().stream()
                .peek(patient -> log.debug("Checking name: {}", patient.getName()))
                .collect(Collectors.partitioningBy(patient -> patient.getName().length() > 5));
        log.info("Patients divided by name length (> 5) : {}", partitionedByNameLength.get(true));

        try {
            Optional<Patient> result = PatientOptional.findPatientBySurname(pulmonology, "Smiths");
            Patient patient = result.orElseThrow(() -> new PatientNotFoundException("Not found!"));
            log.info("Patient Found: {}", patient.getName());
        } catch (PatientNotFoundException e) {
            log.error(e.getMessage());
        }

        try {

            Class<?> employeeClass = Class.forName("com.solvd.hospital.employees.Doctor");

            log.info("Fields:");
            Arrays.stream(employeeClass.getDeclaredFields())
                    .forEach(field -> log.info("Name {} , Type: {} , Modifiers {}",
                            field.getName(), field.getType(), Modifier.toString(field.getModifiers())));

            log.info("Constructors:");
            Arrays.stream(employeeClass.getConstructors())
                    .forEach(constructor -> log.info("Constructor: {} , Parameters: {}",
                            constructor.getName(), Arrays.toString(constructor.getParameterTypes())));

            log.info("Methods:");
            Arrays.stream(employeeClass.getMethods())
                    .forEach(method -> log.info("Name: {} , Return Type: {} , Parameters: {} , Modifiers: {}",
                            method.getName(), method.getReturnType(),
                            Arrays.toString(method.getParameterTypes()),
                            Modifier.toString(method.getModifiers())));

            Constructor<?> constructor = employeeClass.getConstructor(
                    String.class, String.class, String.class, Hierarchy.class);

            Object employee = constructor.newInstance("Tester", "Tested", "Tester", Hierarchy.LEADER);

            Method displayInfo = employeeClass.getMethod("displayInfo");
            displayInfo.invoke(employee);

        } catch (Exception e) {
            log.error("Reflection failed: ", e);
        }
        List<Branch> branches = Arrays.asList(neurology, pulmonology);
        List<Patient> allPatients = branches.stream()
                .map(Branch::getPatients)
                .flatMap(List::stream)
                .toList();

        allPatients.forEach(patient -> log.error("Patient: {} {}", patient.getName(), patient.getSurname()));
    }
}