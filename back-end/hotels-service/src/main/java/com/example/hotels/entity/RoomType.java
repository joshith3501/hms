package com.example.hotels.entity;

import jakarta.persistence.*;

@Entity
public class RoomType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int capacity;
    private double price;
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String n){this.name=n;}
    public int getCapacity(){return capacity;} public void setCapacity(int c){this.capacity=c;}
    public double getPrice(){return price;} public void setPrice(double p){this.price=p;}
}
