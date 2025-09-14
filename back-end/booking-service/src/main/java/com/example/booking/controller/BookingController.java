package com.example.booking.controller;

import com.example.booking.entity.Booking;
import com.example.booking.repository.BookingRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingRepository repo;
    public BookingController(BookingRepository repo){this.repo=repo;}

    @GetMapping
    public List<Booking> list(){ return repo.findAll(); }

    @GetMapping("/hotel/{hotelId}")
    public List<Booking> byHotel(@PathVariable Long hotelId){ return repo.findByHotelId(hotelId); }

    @PostMapping
    public Booking create(@RequestBody Booking b){
        if(b.getStatus()==null) b.setStatus("PENDING");
        return repo.save(b);
    }

    @PutMapping("/{id}")
    public Booking update(@PathVariable Long id, @RequestBody Booking b){
        b.setId(id);
        return repo.save(b);
    }

    @PostMapping("/{id}/checkin")
    public Booking checkIn(@PathVariable Long id){
        Optional<Booking> o = repo.findById(id);
        if(o.isEmpty()) throw new RuntimeException("Booking not found");
        Booking b = o.get();
        b.setStatus("CHECKED_IN");
        return repo.save(b);
    }

    @PostMapping("/{id}/checkout")
    public Booking checkOut(@PathVariable Long id){
        Optional<Booking> o = repo.findById(id);
        if(o.isEmpty()) throw new RuntimeException("Booking not found");
        Booking b = o.get();
        b.setStatus("COMPLETED");
        return repo.save(b);
    }

    @PostMapping("/{id}/cancel")
    public Booking cancel(@PathVariable Long id){
        Optional<Booking> o = repo.findById(id);
        if(o.isEmpty()) throw new RuntimeException("Booking not found");
        Booking b = o.get();
        b.setStatus("CANCELLED");
        return repo.save(b);
    }

    @PostMapping("/{id}/noshow")
    public Booking noShow(@PathVariable Long id){
        Optional<Booking> o = repo.findById(id);
        if(o.isEmpty()) throw new RuntimeException("Booking not found");
        Booking b = o.get();
        b.setStatus("NO_SHOW");
        return repo.save(b);
    }

    // Receptionist: view today's check-ins
    @GetMapping("/checkins/today")
    public List<Booking> todaysCheckins(){
        LocalDate today = LocalDate.now();
        return repo.findByCheckInDate(today);
    }

    // Manager: occupancy dashboard - simple counts for a hotel
    @GetMapping("/hotel/{hotelId}/occupancy")
    public Object occupancy(@PathVariable Long hotelId){
        long occupied = repo.findByHotelIdAndStatus(hotelId, "CHECKED_IN").size();
        long totalBookings = repo.findByHotelId(hotelId).size();
        return java.util.Map.of("hotelId", hotelId, "occupied", occupied, "totalBookings", totalBookings);
    }
}
