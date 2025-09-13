package com.ilpmum.hotel.catalog_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ilpmum.hotel.catalog_service.model.RoomType;
import com.ilpmum.hotel.catalog_service.repo.HotelRepository;
import com.ilpmum.hotel.catalog_service.repo.RoomRepository;
import com.ilpmum.hotel.catalog_service.repo.RoomTypeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin/hotels/{hotelId}/roomtypes")
public class RoomTypeController {
	
	private final RoomTypeRepository roomTypes;
	private final HotelRepository hotels;
	private final RoomRepository rooms;
	
	public RoomTypeController(RoomTypeRepository roomTypes, HotelRepository hotels, RoomRepository rooms) {
		this.roomTypes = roomTypes;
		this.hotels = hotels;
		this.rooms = rooms;
	}
	
	@GetMapping
	public List<RoomType> list(@PathVariable Long hotelId,  @RequestParam(required = false) String name) {
		if(name != null) {
			return roomTypes.findByHotel_IdAndNameIgnoreCase(hotelId, name);
		}
		return roomTypes.findByHotel_Id(hotelId);
	}
	
	@GetMapping("/{id}")
	public RoomType get(@PathVariable long hotelId, @PathVariable long id) {
		return roomTypes.findByIdAndHotel_Id(id, hotelId).orElseThrow(() -> new RuntimeException("RoomType not found"));
	}
	
	@PostMapping
	public RoomType create(@PathVariable("hotelId") Long hotelId ,@RequestBody RoomType body)
	{
		var hotel = hotels.findById(hotelId).orElseThrow(()-> new RuntimeException("Hotel not found"));
		body.setHotel(hotel);
		return roomTypes.save(body);
	}
	
	@PostMapping("/{id}")
	public RoomType update(@PathVariable Long hotelId, @PathVariable Long id, @RequestBody RoomType body) {
		var existing = roomTypes.findByIdAndHotel_Id(id, hotelId).orElseThrow(() -> new RuntimeException("RoomType not found"));
		existing.setName(body.getName());
		existing.setMaxGuests(body.getMaxGuests());
		existing.setBasePrice(body.getBasePrice());
		return roomTypes.save(existing);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRoomType(@PathVariable Long hotelId, @PathVariable Long id) {
		var roomType = roomTypes.findByIdAndHotel_Id(id, hotelId).orElseThrow(() -> new RuntimeException("RoomType not found"));
		
		long count = rooms.countByRoomTypeId(id);
		if(count > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot delete RoomType: Rooms exist for this type");
		}
		roomTypes.delete(roomType);
		
		return ResponseEntity.noContent().build();
	}
}
