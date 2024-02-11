package com.example.govoriigraya.services;

import com.example.govoriigraya.entities.Client;

import java.sql.Date;
import java.util.List;

public interface ClientService {
    void saveClient(Client client);

    void saveClient(String fullname, String phone, String mail);
    List<Client> getClientsByDay(Date date);
}