package org.example.usermicroservice.services;

import org.example.usermicroservice.entities.User;
import org.example.usermicroservice.feignClients.MonopatinFeignClient;
import org.example.usermicroservice.repositories.UserRepository;
import org.example.usermicroservice.utils.HelperUsers;
import org.example.usermicroservice.utils.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    MonopatinFeignClient monopatinFeignClient;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public List<User> getUsersByRole(String role) {
        try {
            UserRoles userRole = UserRoles.valueOf(role.toLowerCase());
            return this.userRepository.getUsersByRole(userRole.name());
        } catch (IllegalArgumentException e) {
            return new ArrayList<>();
        }
    }

    public User save(User user){
        User userNew;
        userNew = userRepository.save(user);
        return userNew;
    }
    public void delete(User user){

        userRepository.delete(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public ResponseEntity<?> getClosestMonopatins(int posx, int posy) {
        ResponseEntity<?> monopatins = this.monopatinFeignClient.getClosestMonopatins(posx, posy);
        return monopatins;
    }
}
