package com.ilpmum.hotel.catalog_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ilpmum.hotel.catalog_service.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
	
	boolean existsByNameIgnoreCaseAndCityIgnoreCase(String name, String city);
	boolean existsByNameIgnoreCaseAndCityIgnoreCaseAndIdNot(String name, String city, Long id);
	
}
