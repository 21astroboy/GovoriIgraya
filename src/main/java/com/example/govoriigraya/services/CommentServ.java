package com.example.govoriigraya.services;

import com.example.govoriigraya.entities.Client;
import com.example.govoriigraya.entities.Comment;

public interface CommentServ {
    void saveComment(Comment comment);

    void saveComment(String fullname, String text);
}
