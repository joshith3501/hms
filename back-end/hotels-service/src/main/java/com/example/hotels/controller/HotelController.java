package com.example.hotels.controller;
import com.example.hotels.entity.Hotel;
import com.example.hotels.repository.HotelRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/hotels")
public class HotelController {
    private final HotelRepository repo;
    public HotelController(HotelRepository repo){this.repo=repo;}

    @GetMapping
    public List<Hotel> list(){ return repo.findAll(); }

    @PostMapping
    public Hotel create(@RequestBody Hotel h){ return repo.save(h); }

    @PutMapping("/{id}")
    public Hotel update(@PathVariable Long id, @RequestBody Hotel h){
        h.setId(id);
        return repo.save(h);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ repo.deleteById(id); }
}
