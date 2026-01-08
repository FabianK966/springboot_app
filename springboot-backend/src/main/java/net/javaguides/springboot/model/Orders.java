package net.javaguides.springboot.model;


import jakarta.persistence.*;

@Entity
@Table( name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Oid;

    @Column(name = "Articles")
    private String Articles;
    @Column(name = "cost")
    private double cost;
    @Column(name = "Quantity")
    private int Quantity;

    public Orders() {

    }

    public Orders(String Articles, double cost, int Quantity) {
        this.Articles = Articles;
        this.cost = cost;
        this.Quantity = Quantity;
    }

    public long getId() {
        return Oid;
    }

    public void setId(long id) {
        this.Oid = id;
    }

    public String getArticles() {
        return Articles;
    }

    public void setArticles(String articles) {
        this.Articles = articles;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {this.cost = cost;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }
}
