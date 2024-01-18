package com.example.govoriigraya.controllers;

import com.example.govoriigraya.entities.Client;
import com.example.govoriigraya.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String saveClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/clients/success"; // Redirect to the success page
    }

    @GetMapping("/clients/success")
    public String showSuccessPage() {
        return "success";
    }

}
