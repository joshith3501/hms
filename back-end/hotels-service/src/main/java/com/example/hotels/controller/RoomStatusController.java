package com.example.hotels.controller;

import com.example.hotels.entity.Room;
import com.example.hotels.repository.RoomRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomStatusController {
    private final RoomRepository repo;

    public RoomStatusController(RoomRepository repo){ this.repo = repo; }

    @PatchMapping("/{roomId}/status")
    public Room updateStatus(@PathVariable Long roomId, @RequestParam String status){
        Room room = repo.findById(roomId).orElseThrow();
        room.setStatus(status);
        return repo.save(room);
    }
}

