package com.ilpmum.hotel.catalog_service.model;
 
import jakarta.persistence.*;
 
@Entity
public class Manager {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false)
    private String name;
 
    @Column(nullable = false, unique = true)
    private String email;
 
    private String phone;
 
    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
 
    // Constructors
    public Manager() {}
 
    public Manager(Long id, String name, String email, String phone, Hotel hotel) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.hotel = hotel;
    }
 
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
 
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
 
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
 
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
 
    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }
}
 