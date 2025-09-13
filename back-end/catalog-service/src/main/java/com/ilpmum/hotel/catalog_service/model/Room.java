package com.ilpmum.hotel.catalog_service.model;

import jakarta.persistence.*;


@Entity
@Table(
		name = "rooms",
		uniqueConstraints = @UniqueConstraint(
				name = "uk_romm_hotel_number",
				columnNames = {"hotel_id", "room_number"}
				)
		)
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	private Hotel hotel;
	@ManyToOne(optional = false)
	private RoomType roomType;
	
	@Column(nullable = false)
	private String roomNumber;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Room(long id, Hotel hotel, RoomType roomType, String roomNumber, Status status) {
		super();
		this.id = id;
		this.hotel = hotel;
		this.roomType = roomType;
		this.roomNumber = roomNumber;
		this.status = status;
	}

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(nullable = false)
	private Status status;
	
	public enum Status { AVAILABLE, MAINTENANCE, BOOKED }

}
