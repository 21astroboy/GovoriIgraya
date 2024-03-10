package com.example.govoriigraya.services;

import com.example.govoriigraya.entities.Actor;
import com.example.govoriigraya.repo.ActorRepo;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

@Service
public class AuthServiceImpl implements AuthService {
    private final ConcurrentMap<String, Actor> tokenToActor = new ConcurrentSkipListMap<>();

    @Autowired
    private ActorRepo actorRepo;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean auth(String token) {
        if (token == null || token.isEmpty()) return false;
        if (tokenToActor.containsKey(token)) return true;
        Optional<Actor> actor = actorRepo.findByToken(token);
        if (actor.isEmpty()) return false;
        tokenToActor.put(token, actor.get());
        return true;
    }

    @Override
    public boolean checkAdmin() {
        return actorRepo.findByRole("admin").isPresent();
    }

    @Override
    public Actor get(String token) {
        Actor res = tokenToActor.get(token);
        if (res == null) {
            res = actorRepo.findByToken(token).get();
            tokenToActor.put(token, res);
        }
        return res;
    }

    @Override
    public void createActor(String phone, String name, String password, String role) {
        phone = phoneService.standatrtise(phone);
        if (actorRepo.findByPhone(phone).isEmpty())
            actorRepo.saveAndFlush(new Actor(phone, name, passwordEncoder.encode(password), role));
    }

    @Override
    public Actor login(String phone, String password) {
        phone = phoneService.standatrtise(phone);
        Optional<Actor> actor = actorRepo.findByPhone(phone);
        if (actor.isEmpty()) return null;
        if (passwordEncoder.matches(password, actor.get().getPassword())) return actor.get();
        return null;
    }
}
