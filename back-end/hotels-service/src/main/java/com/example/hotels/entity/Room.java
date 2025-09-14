package com.example.hotels.entity;

import jakarta.persistence.*;

@Entity
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long hotelId;
    private Long roomTypeId;
    private String status; // AVAILABLE, OCCUPIED, MAINTENANCE
    private String roomNumber;
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getHotelId(){return hotelId;} public void setHotelId(Long h){this.hotelId=h;}
    public Long getRoomTypeId(){return roomTypeId;} public void setRoomTypeId(Long r){this.roomTypeId=r;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
    public String getRoomNumber(){return roomNumber;} public void setRoomNumber(String rn){this.roomNumber=rn;}
}
