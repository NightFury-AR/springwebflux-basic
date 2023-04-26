package com.reactivelearning.service;

import com.reactivelearning.dto.UserDTO;
import com.reactivelearning.mapper.UserMapper;
import com.reactivelearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired private UserRepository repository;
    @Autowired private UserMapper mapper;

    public Flux<UserDTO> getAllUsers() {
        return this.repository
                .findAll()
                .map(mapper::toDTO);
    }

    public Mono<UserDTO> getUserById(Integer id) {
        return this.repository
                .findById(id)
                .map(mapper::toDTO);
    }

    public Mono<UserDTO> createUser(Mono<UserDTO> userDTOMono) {
        return userDTOMono
                .map(mapper::toEntity)
                .flatMap(this.repository::save)
                .map(mapper::toDTO);

    }

    public Mono<UserDTO> updateUser(Integer id,Mono<UserDTO> userDTOMono) {
        return this.repository
                .findById(id)
                .flatMap(user -> userDTOMono
                        .map(mapper::toEntity)
                        .doOnNext(user1 -> user1.setId(user.getId())))
                .map(mapper::toDTO);
    }

    public Mono<Void> deleteUserById(Integer id) {
        return this.repository
                .deleteById(id);
    }

}
