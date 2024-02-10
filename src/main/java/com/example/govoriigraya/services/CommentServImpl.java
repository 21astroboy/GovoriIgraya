package com.example.govoriigraya.services;

import com.example.govoriigraya.entities.Comment;
import com.example.govoriigraya.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServImpl implements CommentServ{
@Autowired
private CommentRepo repo;
    @Override
    public void saveComment(Comment comment) {
        repo.save(comment);
    }

    @Override
    public void saveComment(String fullname, String text) {
        Comment comment=new Comment(fullname,text);
        repo.save(comment);
    }
}
