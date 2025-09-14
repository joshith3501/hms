package com.example.user.controller;

import com.example.user.entity.UserAccount;
import com.example.user.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {
    private final UserRepository repo;
    public UserController(UserRepository repo){this.repo=repo;}

    @GetMapping
    public List<UserAccount> list(){ return repo.findAll(); }

    @PostMapping
    public UserAccount create(@RequestBody UserAccount u){ return repo.save(u); }

    @PutMapping("/{id}")
    public UserAccount update(@PathVariable Long id, @RequestBody UserAccount u){
        u.setId(id);
        return repo.save(u);
    }

    @PatchMapping("/{id}/activate")
    public UserAccount setActive(@PathVariable Long id, @RequestParam boolean active){
        Optional<UserAccount> o = repo.findById(id);
        if(o.isEmpty()) throw new RuntimeException("User not found");
        UserAccount u = o.get();
        u.setActive(active);
        return repo.save(u);
    }
}
