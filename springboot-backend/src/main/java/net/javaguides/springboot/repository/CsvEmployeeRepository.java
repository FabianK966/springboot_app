package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CsvEmployeeRepository {

    private static final String CSV_FILE = "data.csv";
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Employee> findAll() {
        System.out.println("📁 Lese CSV-Datei: " + CSV_FILE);
        List<Employee> employees = new ArrayList<>();

        try {
            Path path = Paths.get(CSV_FILE);

            // Wenn Datei nicht existiert, erstelle sie mit Beispieldaten
            if (!Files.exists(path)) {
                System.out.println("📄 Datei existiert nicht, erstelle...");
                createSampleData();
                return findAll(); // Rekursiv aufrufen
            }

            // CSV manuell lesen
            List<String> lines = Files.readAllLines(path);

            if (lines.isEmpty()) {
                System.out.println("⚠️ Datei ist leer, erstelle Beispieldaten...");
                createSampleData();
                lines = Files.readAllLines(path);
            }

            // Überspringe Header (erste Zeile)
            boolean skipHeader = true;
            for (String line : lines) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 4) {
                        try {
                            Employee emp = new Employee(
                                    Long.parseLong(parts[0].trim()),
                                    parts[1].trim(),
                                    parts[2].trim(),
                                    parts[3].trim()
                            );
                            employees.add(emp);
                            System.out.println("✅ Gelesen: " + emp);
                        } catch (NumberFormatException e) {
                            System.err.println("❌ Fehler beim Parsen der Zeile: " + line);
                        }
                    }
                }
            }

            // ID-Zähler setzen
            if (!employees.isEmpty()) {
                long maxId = employees.stream()
                        .mapToLong(Employee::getId)
                        .max()
                        .orElse(0);
                idCounter.set(maxId + 1);
                System.out.println("🆔 Nächste ID: " + idCounter.get());
            }

            System.out.println("📊 Gefunden: " + employees.size() + " Employees");

        } catch (Exception e) {
            System.err.println("❌ Fehler beim Lesen: " + e.getMessage());
            // Fallback: Beispieldaten zurückgeben
            return getFallbackEmployees();
        }

        return employees;
    }

    private void createSampleData() {
        try {
            System.out.println("🎨 Erstelle Beispieldaten...");

            // Beispieldaten
            List<Employee> sampleEmployees = new ArrayList<>();
            sampleEmployees.add(new Employee(1L, "John", "Doe", "john@example.com"));
            sampleEmployees.add(new Employee(2L, "Jane", "Smith", "jane@example.com"));
            sampleEmployees.add(new Employee(3L, "Bob", "Johnson", "bob@example.com"));

            // In CSV schreiben
            try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
                // Header
                writer.println("id,firstName,lastName,email");

                // Daten
                for (Employee emp : sampleEmployees) {
                    writer.println(String.format("%d,%s,%s,%s",
                            emp.getId(),
                            emp.getFirstName(),
                            emp.getLastName(),
                            emp.getEmailId()
                    ));
                }
            }

            idCounter.set(4L);
            System.out.println("✅ Beispieldaten erstellt in: " + Paths.get(CSV_FILE).toAbsolutePath());

        } catch (Exception e) {
            System.err.println("❌ Fehler beim Erstellen der Beispieldaten: " + e.getMessage());
        }
    }

    private List<Employee> getFallbackEmployees() {
        System.out.println("🔄 Verwende Fallback-Daten...");
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John", "Doe", "john@example.com"));
        employees.add(new Employee(2L, "Jane", "Smith", "jane@example.com"));
        return employees;
    }

    public Optional<Employee> findById(Long id) {
        return findAll().stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst();
    }

    public Employee save(Employee employee) {
        System.out.println("💾 Speichere Employee: " + employee);

        List<Employee> employees = findAll();

        if (employee.getId() == null || employee.getId() == 0) {
            employee.setId(idCounter.getAndIncrement());
            employees.add(employee);
            System.out.println("🆕 Neuer Employee mit ID: " + employee.getId());
        } else {
            employees.removeIf(emp -> emp.getId().equals(employee.getId()));
            employees.add(employee);
            System.out.println("✏️ Employee aktualisiert: " + employee.getId());
        }

        saveAll(employees);
        return employee;
    }

    public void deleteById(Long id) {
        System.out.println("🗑️ Lösche Employee mit ID: " + id);
        List<Employee> employees = findAll();
        employees.removeIf(emp -> emp.getId().equals(id));
        saveAll(employees);
    }

    private void saveAll(List<Employee> employees) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
            // Header
            writer.println("id,firstName,lastName,email");

            // Daten
            for (Employee emp : employees) {
                writer.println(String.format("%d,%s,%s,%s",
                        emp.getId(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmailId()
                ));
            }

            System.out.println("✅ " + employees.size() + " Employees gespeichert");

        } catch (Exception e) {
            System.err.println("❌ Fehler beim Speichern: " + e.getMessage());
        }
    }
}