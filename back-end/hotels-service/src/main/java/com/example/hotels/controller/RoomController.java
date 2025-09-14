package com.example.hotels.controller;
import com.example.hotels.entity.Room;
import com.example.hotels.repository.RoomRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/hotels/{hotelId}/rooms")
public class RoomController {
    private final RoomRepository repo;
    public RoomController(RoomRepository repo){this.repo=repo;}

    @GetMapping
    public List<Room> list(@PathVariable Long hotelId){ return repo.findByHotelId(hotelId); }

    @PostMapping
    public Room add(@PathVariable Long hotelId, @RequestBody Room room){
        room.setHotelId(hotelId);
        return repo.save(room);
    }

    @PutMapping("/{roomId}")
    public Room update(@PathVariable Long hotelId, @PathVariable Long roomId, @RequestBody Room room){
        room.setId(roomId);
        room.setHotelId(hotelId);
        return repo.save(room);
    }

    @DeleteMapping("/{roomId}")
    public void delete(@PathVariable Long roomId){
        repo.deleteById(roomId);
    }
}
