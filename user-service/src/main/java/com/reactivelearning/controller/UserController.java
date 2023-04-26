package com.reactivelearning.controller;

import com.reactivelearning.dto.UserDTO;
import com.reactivelearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired private UserService service;


    @GetMapping("/list")
    public Flux<UserDTO> getUserList() {
        return this.service.getAllUsers();
    }

    @GetMapping("{userId}")
    public Mono<UserDTO> getUserById(@PathVariable("userId") Integer id) {
        return this.service
                .getUserById(id);
    }

    @PostMapping
    public Mono<UserDTO> createNewUser(@RequestBody Mono<UserDTO> userDTOMono) {
        return this.service
                .createUser(userDTOMono);
    }

    @PutMapping("{userId}")
    public Mono<UserDTO> updateUser(@PathVariable("userId") Integer userId, @RequestBody Mono<UserDTO> userDTOMono) {
        return this.service
                .updateUser(userId,userDTOMono);
    }

    @DeleteMapping("{userId}")
    public Mono<Void> deleteUser(@PathVariable("userId") Integer userId) {
        return this.service
                .deleteUserById(userId);
    }

}
