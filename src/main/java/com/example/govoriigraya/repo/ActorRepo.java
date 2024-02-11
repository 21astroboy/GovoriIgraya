package com.example.govoriigraya.repo;

import com.example.govoriigraya.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepo extends JpaRepository<Actor, Long> {
    Optional<Actor> findByToken(String token);
    Optional<Actor> findByPhoneAndPassword(String phone, String password);
}
