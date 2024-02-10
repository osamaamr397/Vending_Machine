package com.Amr.vending.machine.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "productName")
    private String productName;
    @Column(name = "amountAvailable")
    private int amountAvailable;
    @Column(name = "cost")
    private int cost;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sellerId")
    private User user;
    public Product(){}

    public Product(String productName, int amountAvailable, int cost) {
        this.productName = productName;
        this.amountAvailable = amountAvailable;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(int amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
