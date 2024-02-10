package com.example.govoriigraya.controllers;

import com.example.govoriigraya.entities.Client;
import com.example.govoriigraya.services.ClientService;
import com.example.govoriigraya.services.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/showForm")
    public String showForm(Model model) {
        model.addAttribute("client", new Client());
        return "client-form";
    }

    @PostMapping("/saveClient")
    public String saveClient(
            @RequestParam("fullname") String fullname,
            @RequestParam("phone") String phone,
            @RequestParam("mail") String mail) {

        clientService.saveClient(fullname, phone, mail);
        return "redirect:/clients/success";
    }

    @GetMapping("/clients/success")
    public String showSuccessPage() {
        return "success";
    }
}
