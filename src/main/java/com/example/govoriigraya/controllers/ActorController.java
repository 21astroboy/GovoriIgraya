package com.example.govoriigraya.controllers;

import com.example.govoriigraya.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ActorController {
    @Autowired
    private AuthService authService;

    @PostMapping(value = "/actor/admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createAdmin(@RequestParam MultiValueMap<String, String> form) {
        if (authService.checkAdmin()) return ResponseEntity.badRequest().build();
        authService.createActor(form.getFirst("phone"), form.getFirst("firstname"), form.getFirst("password"), "admin");
        return ResponseEntity.accepted().build();
    }

    @PostMapping(value = "/actor/moder", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createModer(@RequestParam MultiValueMap<String, String> form) {
        authService.createActor(form.getFirst("phone"), form.getFirst("firstname"), form.getFirst("password"), "moder");
        return ResponseEntity.accepted().build();
    }

    @PostMapping(value = "/actor/doctor", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createDoctor(@RequestParam MultiValueMap<String, String> form) {
        authService.createActor(form.getFirst("phone"),  form.getFirst("firstname"), form.getFirst("password"), "doctor");
        return ResponseEntity.accepted().build();
    }
}
