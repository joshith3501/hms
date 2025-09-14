package com.example.hotels.entity;

import jakarta.persistence.*;

@Entity
public class Hotel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String address;
    private String status; // ACTIVE/INACTIVE
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String n){this.name=n;}
    public String getCity(){return city;} public void setCity(String c){this.city=c;}
    public String getAddress(){return address;} public void setAddress(String a){this.address=a;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
}
