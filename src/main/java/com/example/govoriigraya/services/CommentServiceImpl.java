package com.example.govoriigraya.services;

import com.example.govoriigraya.entities.Comment;
import com.example.govoriigraya.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Override
    public void saveComment(String fullname, String text) {
        commentRepo.saveAndFlush(new Comment(fullname, text));
    }
}
