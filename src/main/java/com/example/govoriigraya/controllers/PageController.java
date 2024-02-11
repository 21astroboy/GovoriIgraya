package com.example.govoriigraya.controllers;

import com.example.govoriigraya.entities.Actor;
import com.example.govoriigraya.model.Credentials;
import com.example.govoriigraya.services.AuthService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {
    @Autowired
    private AuthService authService;
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/appointment")
    public String appointment() {
        return "appointment";
    }

    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }

    @GetMapping("/privacy")
    public String privacy() {
        return "privacy";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("credentials", new Credentials());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Credentials credentials, HttpServletResponse res) {
        Actor actor = authService.login(credentials.getPhone(), credentials.getPassword());
        if (actor == null) return "redirect:login";
        Cookie token = new Cookie("token", actor.getToken());
        token.setMaxAge(86400);
        token.setHttpOnly(true);
        res.addCookie(token);
        return "redirect:" + actor.getRole() + "-cabinet";
    }

    @GetMapping("/admin-cabinet")
    public String adminCabinet(@CookieValue("token") @Nullable String token) {
        if (!authService.auth(token)) return "redirect:login";
        Actor actor = authService.get(token);
        if (!"admin".equals(actor.getRole())) return "redirect:login";
        return "admin-cabinet";
    }

    @GetMapping("/moder-cabinet")
    public String moderCabinet(@CookieValue("token") @Nullable String token, Model model) {
        if (!authService.auth(token)) return "redirect:login";
        Actor actor = authService.get(token);
        if (!"moder".equals(actor.getRole())) return "redirect:login";
        model.addAttribute("name", actor.getFirstname());
        return "moder-cabinet";
    }
}
