package net.javaguides.springboot.model;

public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;

    public Employee() {}

    public Employee(Long id, String firstName, String lastName, String emailId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, firstName='%s', lastName='%s', emailId='%s'}",
                id, firstName, lastName, emailId);
    }
}