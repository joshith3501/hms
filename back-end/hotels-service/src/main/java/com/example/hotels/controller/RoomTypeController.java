package com.example.hotels.controller;
import com.example.hotels.entity.RoomType;
import com.example.hotels.repository.RoomTypeRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/room-types")
public class RoomTypeController {
    private final RoomTypeRepository repo;
    public RoomTypeController(RoomTypeRepository repo){this.repo=repo;}
    @GetMapping public List<RoomType> list(){ return repo.findAll(); }
    @PostMapping public RoomType create(@RequestBody RoomType rt){ return repo.save(rt); }
    @PutMapping("/{id}") public RoomType update(@PathVariable Long id, @RequestBody RoomType rt){ rt.setId(id); return repo.save(rt); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ repo.deleteById(id); }
}
