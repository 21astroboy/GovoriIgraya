package com.example.govoriigraya.services;

import com.example.govoriigraya.entities.Client;

public interface ClientService {
    void saveClient(Client client);

    void saveClient(String fullname, String phone, String mail);
}