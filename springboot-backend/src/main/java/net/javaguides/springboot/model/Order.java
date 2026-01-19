package net.javaguides.springboot.model;

import jakarta.persistence.*;

@Entity
@Table( name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "articles")
    private String articles;
    @Column(name = "cost")
    private Double cost;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Order() {
    }

    public Order(String articles, double cost, int quantity, Employee employee) {
        this.articles = articles;
        this.cost = cost;
        this.quantity = quantity;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticles() {
        return articles;
    }

    public void setArticles(String articles) {
        this.articles = articles;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {this.cost = cost;}

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Employee getEmployee() { return employee; }

    public void setEmployee(Employee employee) { this.employee = employee; }
}
