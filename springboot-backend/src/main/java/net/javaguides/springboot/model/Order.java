package net.javaguides.springboot.model;


import jakarta.persistence.*;

@Entity
@Table( name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Oid;

    @Column(name = "articles")
    private String articles;
    @Column(name = "cost")
    private double cost;
    @Column(name = "quantity")
    private int quantity;

    public Order() {
    }

    public Order(String articles, double cost, int quantity) {
        this.articles = articles;
        this.cost = cost;
        this.quantity = quantity;
    }

    public long getId() {
        return Oid;
    }

    public void setId(long id) {
        this.Oid = id;
    }

    public String getArticles() {
        return articles;
    }

    public void setArticles(String articles) {
        this.articles = articles;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
