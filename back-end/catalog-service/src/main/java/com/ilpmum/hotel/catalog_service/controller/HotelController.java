package com.ilpmum.hotel.catalog_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ilpmum.hotel.catalog_service.model.Hotel;
import com.ilpmum.hotel.catalog_service.model.Hotel.Status;
import com.ilpmum.hotel.catalog_service.repo.HotelRepository;
import com.ilpmum.hotel.catalog_service.repo.RoomTypeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin/hotels")
public class HotelController {
	
	private final HotelRepository hotels;
	private final RoomTypeRepository roomTypes;
	
	public HotelController(HotelRepository hotels, RoomTypeRepository roomTypes) {
		this.hotels = hotels;
		this.roomTypes = roomTypes;
	}
	
	@GetMapping
	public List<Hotel> list() {
		return hotels.findAll();
	}
	
	@GetMapping("/{id}")
	public Hotel get(@PathVariable long id) {
		return hotels.findById(id).orElseThrow();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Hotel body) {
		if(body.getStatus() == null)
			body.setStatus(Hotel.Status.ACTIVE);
		if(hotels.existsByNameIgnoreCaseAndCityIgnoreCase(body.getName(), body.getCity())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("A hotel with the same name in this city already exists");
		}
		return  ResponseEntity.status(HttpStatus.CREATED).body(hotels.save(body));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel updatedHotel){
		return hotels.findById(id).map(existing -> {
			existing.setName(updatedHotel.getName());
			existing.setCity(updatedHotel.getCity());
			existing.setAddress(updatedHotel.getAddress());
			existing.setStatus(updatedHotel.getStatus());
			return ResponseEntity.ok(hotels.save(existing));
			
		})
			.orElse(ResponseEntity.notFound().build());	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
		var hotel = hotels.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));
		
		if(hotel.getStatus() != Status.INACTIVE) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hotel must be set to INACTIVE before deletion");
		}
		
		roomTypes.deleteByHotelId(id);
		
		hotels.delete(hotel);
		return ResponseEntity.noContent().build();
	}

}
