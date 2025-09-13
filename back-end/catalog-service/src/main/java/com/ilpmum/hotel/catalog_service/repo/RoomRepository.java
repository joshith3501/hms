package com.ilpmum.hotel.catalog_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilpmum.hotel.catalog_service.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
	
	List<Room> findByHotelId(Long hotelId);
	List<Room> findByHotelIdAndStatus(Long hotelId, Room.Status status);
	List<Room> findByHotelIdAndRoomTypeId(Long hotelId, Long roomTypeId);
	long countByRoomTypeId(Long roomTypeId);

}
