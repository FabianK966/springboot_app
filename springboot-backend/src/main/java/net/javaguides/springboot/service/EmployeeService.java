package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.CsvEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private CsvEmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        System.out.println("🚀 Service: Alle Employees abrufen");
        List<Employee> employees = employeeRepository.findAll();
        System.out.println("✅ Service: " + employees.size() + " Employees gefunden");
        return employees;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        System.out.println("🔍 Service: Employee mit ID " + id + " suchen");
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        System.out.println("➕ Service: Neuen Employee erstellen");
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        System.out.println("✏️ Service: Employee mit ID " + id + " aktualisieren");
        return employeeRepository.findById(id)
                .map(existing -> {
                    existing.setFirstName(employeeDetails.getFirstName());
                    existing.setLastName(employeeDetails.getLastName());
                    existing.setEmailId(employeeDetails.getEmailId());
                    return employeeRepository.save(existing);
                })
                .orElse(null);
    }

    public boolean deleteEmployee(Long id) {
        System.out.println("🗑️ Service: Employee mit ID " + id + " löschen");
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}