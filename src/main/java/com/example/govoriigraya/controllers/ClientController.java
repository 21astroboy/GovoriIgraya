package com.example.govoriigraya.controllers;

import com.example.govoriigraya.entities.Client;
import com.example.govoriigraya.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;
    @PostMapping("/appointment")
    public ResponseEntity<Object> appointment(@RequestBody Client client) {
        clientService.saveClient(client);
        return ResponseEntity.ok().build();
    }

}
