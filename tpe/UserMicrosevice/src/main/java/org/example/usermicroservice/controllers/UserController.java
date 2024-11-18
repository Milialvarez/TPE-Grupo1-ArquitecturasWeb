package org.example.usermicroservice.controllers;

import jakarta.xml.ws.http.HTTPBinding;
import org.example.usermicroservice.entities.User;
import org.example.usermicroservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping //Andando
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}") //Andando
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        try{
            User user = userService.getUserById(id);
            if (user == null) {
                return  ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        }catch(Exception e){
            return  ResponseEntity.status(500).build();
        }

    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        try{
            User userNew = userService.save(user);
            return ResponseEntity.ok(userNew);
        }catch(Exception e){
            return  ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/monopatins/location/{posx}/{posy}")
    public ResponseEntity<?> getClosestMonopatins(@PathVariable("posx") int posx, @PathVariable("posy") int posy){
        return ResponseEntity.status(200).body(this.userService.getClosestMonopatins(posx, posy));
    }

    @GetMapping("/role/{role}") //Andando
    public ResponseEntity<?> getUsersByRole(@PathVariable("role") String role){
        List<User> users = this.userService.getUsersByRole(role);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(users);
    }
}
