package com.example.booking.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long hotelId;
    private Long roomId;
    private Long userId; // who created the booking (receptionist or guest id)
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status; // PENDING, CONFIRMED, CHECKED_IN, COMPLETED, CANCELLED, NO_SHOW
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getHotelId(){return hotelId;} public void setHotelId(Long hotelId){this.hotelId=hotelId;}
    public Long getRoomId(){return roomId;} public void setRoomId(Long roomId){this.roomId=roomId;}
    public Long getUserId(){return userId;} public void setUserId(Long userId){this.userId=userId;}
    public LocalDate getCheckInDate(){return checkInDate;} public void setCheckInDate(LocalDate d){this.checkInDate=d;}
    public LocalDate getCheckOutDate(){return checkOutDate;} public void setCheckOutDate(LocalDate d){this.checkOutDate=d;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
}
