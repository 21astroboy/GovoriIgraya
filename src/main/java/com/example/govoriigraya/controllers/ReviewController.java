package com.example.govoriigraya.controllers;

import com.example.govoriigraya.services.CommentService;
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
public class ReviewController {
    @Autowired
    private CommentService commentService;
    @PostMapping(value = "/review", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> review(@RequestParam MultiValueMap<String, String> form) {
        commentService.saveComment(form.getFirst("name"), form.getFirst("review"));
        return ResponseEntity.ok().build();
    }
}
