package org.example.usermicroservice.controllers;

import jakarta.xml.ws.http.HTTPBinding;
import org.example.usermicroservice.entities.User;
import org.example.usermicroservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/monopatins/location/{posx}/{posy}")
    public ResponseEntity<?> getClosestMonopatins(@PathVariable("posx") int posx, @PathVariable("posy") int posy){
        return ResponseEntity.status(200).body(this.userService.getClosestMonopatins(posx, posy));
    }


}
