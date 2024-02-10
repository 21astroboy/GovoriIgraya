package com.example.govoriigraya.controllers;

import com.example.govoriigraya.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;
    @PostMapping(value = "/appointment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> appointment(@RequestParam MultiValueMap<String, String> form) {

        clientService.saveClient(form.getFirst("name"), form.getFirst("phone"), form.getFirst("email"));
        return ResponseEntity.ok().build();
    }

}
