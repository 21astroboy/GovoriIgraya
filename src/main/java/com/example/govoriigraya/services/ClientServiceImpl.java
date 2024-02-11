package com.example.govoriigraya.services;

import com.example.govoriigraya.entities.Client;
import com.example.govoriigraya.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.*;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepo repository;
    @Autowired
    private PhoneService phoneService;

    @Override
    public void saveClient(Client client) {
        repository.save(client);
    }

    @Override
    public void saveClient(String fullname, String phone, String mail) {
        Client client = new Client(fullname, phoneService.standatrtise(phone), mail);
        repository.saveAndFlush(client);
    }

    @Override
    public List<Client> getClientsByDay(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC+3"));
        LocalDate localDate = zonedDateTime.toLocalDate();
        Timestamp start = Timestamp.valueOf(LocalDateTime.of(localDate, LocalTime.MIN));
        Timestamp end = Timestamp.valueOf(LocalDateTime.of(localDate, LocalTime.MAX));
        return repository.findAllByDateBetween(start, end);
    }
}