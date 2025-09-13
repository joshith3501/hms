package com.ilpmum.hotel.catalog_service.controller;
 
import com.ilpmum.hotel.catalog_service.model.Hotel;
import com.ilpmum.hotel.catalog_service.model.Manager;
import com.ilpmum.hotel.catalog_service.repo.HotelRepository;
import com.ilpmum.hotel.catalog_service.repo.ManagerRepository;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/admin/managers")
@CrossOrigin(origins = "http://localhost:4200")
public class ManagerController {
 
    private final ManagerRepository managers;
    private final HotelRepository hotels;
 
    public ManagerController(ManagerRepository managers, HotelRepository hotels) {
        this.managers = managers;
        this.hotels = hotels;
    }
 
    @GetMapping
    public List<Manager> list() {
        return managers.findAll();
    }
 
    @GetMapping("/{id}")
    public Manager get(@PathVariable Long id) {
        return managers.findById(id).orElseThrow();
    }
 
    @PostMapping
    public Manager create(@RequestBody Manager manager) {
        if (manager.getHotel() != null) {
            Hotel hotel = hotels.findById(manager.getHotel().getId()).orElseThrow();
            manager.setHotel(hotel);
        }
        return managers.save(manager);
    }
 
    @PutMapping("/{id}")
    public Manager update(@PathVariable Long id, @RequestBody Manager body) {
        var existing = managers.findById(id).orElseThrow();
        existing.setName(body.getName());
        existing.setEmail(body.getEmail());
        existing.setPhone(body.getPhone());
 
        if (body.getHotel() != null) {
            Hotel hotel = hotels.findById(body.getHotel().getId()).orElseThrow();
            existing.setHotel(hotel);
        }
 
        return managers.save(existing);
    }
 
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        managers.deleteById(id);
    }
}
 