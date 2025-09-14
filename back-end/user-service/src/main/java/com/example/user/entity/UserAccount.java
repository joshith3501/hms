package com.example.user.entity;

import jakarta.persistence.*;

@Entity
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String role; // ADMIN, RECEPTIONIST, MANAGER
    private boolean active = true;
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String n){this.name=n;}
    public String getEmail(){return email;} public void setEmail(String e){this.email=e;}
    public String getRole(){return role;} public void setRole(String r){this.role=r;}
    public boolean isActive(){return active;} public void setActive(boolean a){this.active=a;}
}
