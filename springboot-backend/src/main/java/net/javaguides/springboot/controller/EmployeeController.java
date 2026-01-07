package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        System.out.println("🌐 GET /api/v1/employees aufgerufen");

        try {
            List<Employee> employees = employeeService.getAllEmployees();
            System.out.println("📤 Sende " + employees.size() + " Employees an Client");

            return ResponseEntity.ok(employees);

        } catch (Exception e) {
            System.err.println("❌ Fehler in GET /employees: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        System.out.println("🔍 GET /api/v1/employees/" + id + " aufgerufen");

        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        System.out.println("➕ POST /api/v1/employees aufgerufen");

        // Sicherstellen, dass ID 0 ist für neue Employee
        employee.setId(0L);
        Employee created = employeeService.createEmployee(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
                                                   @RequestBody Employee employeeDetails) {
        System.out.println("✏️ PUT /api/v1/employees/" + id + " aufgerufen");

        Employee updated = employeeService.updateEmployee(id, employeeDetails);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        System.out.println("🗑️ DELETE /api/v1/employees/" + id + " aufgerufen");

        return employeeService.deleteEmployee(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("✅ API ist online! Zeit: " + System.currentTimeMillis());
    }
}