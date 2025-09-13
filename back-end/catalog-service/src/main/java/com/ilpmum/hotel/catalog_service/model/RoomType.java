package com.ilpmum.hotel.catalog_service.model;

import jakarta.persistence.*;

@Entity
public class RoomType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	private Hotel hotel;
//	
//	@Enumerated(EnumType.STRING)
//	@Column(nullable = false)
//	private Type type;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false)
	private int maxGuests;
	
	
	@Column(nullable = false)
	private double basePrice;
	
//	public enum Type {Regal, Statesmen, Artist}

	public RoomType() {
		super();
		// TODO Auto-generated constructor stub
	}

public RoomType(Long id, Hotel hotel,String name, int maxGuests, double basePrice) {
	super();
	this.id = id;
	this.hotel = hotel;
	this.name = name;
	this.maxGuests = maxGuests;
	this.basePrice = basePrice;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Hotel getHotel() {
	return hotel;
}

public void setHotel(Hotel hotel) {
	this.hotel = hotel;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getMaxGuests() {
	return maxGuests;
}

public void setMaxGuests(int maxGuests) {
	this.maxGuests = maxGuests;
}

public double getBasePrice() {
	return basePrice;
}

public void setBasePrice(double basePrice) {
	this.basePrice = basePrice;
}

	
//	public RoomType(long id, Hotel hotel, Type type, int maxGuests, double basePrice) {
//		super();
//		this.id = id;
//		this.hotel = hotel;
//		this.type = type;
//		this.maxGuests = maxGuests;
//		this.basePrice = basePrice;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public Hotel getHotel() {
//		return hotel;
//	}
//
//	public void setHotel(Hotel hotel) {
//		this.hotel = hotel;
//	}
//
//	public Type getType() {
//		return type;
//	}
//
//	public void setType(Type type) {
//		this.type = type;
//	}
//
//	public int getMaxGuests() {
//		return maxGuests;
//	}
//
//	public void setMaxGuests(int maxGuests) {
//		this.maxGuests = maxGuests;
//	}
//
//	public double getBasePrice() {
//		return basePrice;
//	}
//
//	public void setBasePrice(double basePrice) {
//		this.basePrice = basePrice;
//	}
//	
	
	
}
