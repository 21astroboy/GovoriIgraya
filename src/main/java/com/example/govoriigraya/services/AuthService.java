package com.example.govoriigraya.services;

import com.example.govoriigraya.entities.Actor;

public interface AuthService {
    boolean checkAdmin();
    boolean auth(String token);
    Actor get(String token);
    void createActor(String phone, String name, String password, String role);
    Actor login(String phone, String password);
}
