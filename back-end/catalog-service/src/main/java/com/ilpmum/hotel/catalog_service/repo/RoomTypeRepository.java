package com.ilpmum.hotel.catalog_service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilpmum.hotel.catalog_service.model.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
	
	List<RoomType> findByHotel_Id(Long hotelId);
	Optional<RoomType> findByIdAndHotel_Id(Long id, Long hotelId);
	List<RoomType> findByHotel_IdAndNameIgnoreCase(Long hotelId, String name);
	void deleteByHotelId(Long hotelId);
	
}
