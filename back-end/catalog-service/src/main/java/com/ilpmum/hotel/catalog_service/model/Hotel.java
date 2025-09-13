package com.ilpmum.hotel.catalog_service.model;

import jakarta.persistence.*;

@Entity
@Table(
		name = "hotel",
		uniqueConstraints = @UniqueConstraint(columnNames = {"name", "city"})
		)
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String city;
	private String address;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;
	
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	public Hotel(long id, String name, String city, String address, Status status) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.status = status;
	}



	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}



	public enum Status {ACTIVE, INACTIVE};
}
