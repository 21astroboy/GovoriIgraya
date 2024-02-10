package com.example.govoriigraya.controllers;

import com.example.govoriigraya.services.CommentServImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CommentController {
    @Autowired
    private CommentServImpl service;

}
