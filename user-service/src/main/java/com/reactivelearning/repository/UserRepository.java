package com.reactivelearning.repository;

import com.reactivelearning.entity.User;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface UserRepository extends ReactiveCrudRepository<User,Integer> {

    @Modifying
    @Query(
            "UPDATE account_user SET BALANCE = BALANCE - :amount WHERE ID = :userId and BALANCE >= :amount"
    )
    Mono<Boolean> updateUserBalance(Integer userId, Integer amount);
}


