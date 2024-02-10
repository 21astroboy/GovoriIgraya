package com.example.govoriigraya.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "text")
    private String text;

    public Comment(String fullname, String text) {
        this.fullname = fullname;
        this.text = text;
    }
}
