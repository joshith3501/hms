package com.ilpmum.hotel.catalog_service.controller;


import java.util.*;

import org.springframework.web.bind.annotation.*;

import com.ilpmum.hotel.catalog_service.model.Room;
import com.ilpmum.hotel.catalog_service.repo.HotelRepository;
import com.ilpmum.hotel.catalog_service.repo.RoomRepository;
import com.ilpmum.hotel.catalog_service.repo.RoomTypeRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin/hotels/{hotelId}/rooms")
public class RoomController {
	
	private final RoomRepository rooms;
	private final RoomTypeRepository roomTypes;
	private final HotelRepository hotels;
	
	public RoomController(RoomRepository rooms, RoomTypeRepository roomTypes, HotelRepository hotels)
	{
		this.rooms = rooms;
		this.roomTypes = roomTypes;
		this.hotels = hotels;
	}
	
	@GetMapping
	public List<Room> list(@PathVariable long hotelId, @RequestParam(required = false) Room.Status status,
	@RequestParam(required = false) Long roomTypeId) {
		if(status != null)
			return rooms.findByHotelIdAndStatus(hotelId, status);
		if(roomTypeId != null)
			return rooms.findByHotelIdAndRoomTypeId(hotelId, roomTypeId);
		return rooms.findByHotelId(hotelId);
	}
	
	@GetMapping("/{id}")
	public Room get(@PathVariable long hotelId, @PathVariable long id)
	{
		return rooms.findById(id).orElseThrow();
	}
	
	@PostMapping
	public Room create(@PathVariable long hotelId, @RequestParam long roomTypeId, @RequestBody Room body) {
		var hotel = hotels.findById(hotelId).orElseThrow();
		var type = roomTypes.findById(roomTypeId).orElseThrow();
		body.setHotel(hotel);
		body.setRoomType(type);
		if(body.getStatus() == null)
			body.setStatus(Room.Status.AVAILABLE);
		return rooms.save(body);
	}
	
	@PutMapping("/{id}")
	public Room udate(@PathVariable long hotelId, @PathVariable long id, @RequestBody Room body) {
		var existing = rooms.findById(id).orElseThrow();
		existing.setRoomNumber(body.getRoomNumber());
		existing.setStatus(body.getStatus());
		if(body.getRoomType() != null && body.getRoomType().getId() != 0) {
			var type = roomTypes.findById(body.getRoomType().getId()).orElseThrow();
			existing.setRoomType(type);
		}
		return rooms.save(existing);
	}

}
