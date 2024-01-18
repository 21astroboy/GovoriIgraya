package com.example.govoriigraya.services;

import com.example.govoriigraya.entities.Client;
import com.example.govoriigraya.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepo repository;

    @Override
    public void saveClient(Client client) {
        repository.save(client);
    }

    @Override
    public void saveClient(String fullname, String phone, String mail) {
        Client client = new Client(fullname, phone, mail);
        repository.save(client);
    }
}