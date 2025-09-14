package com.example.hotels.repository;
import com.example.hotels.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
public interface HotelRepository extends JpaRepository<Hotel, Long> {}
