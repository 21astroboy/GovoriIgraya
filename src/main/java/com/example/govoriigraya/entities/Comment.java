package com.example.govoriigraya.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullname")
    @NotEmpty(message = "Fullname cannot be empty")
    @Pattern(regexp = "^[А-Яа-яЁ\\s\\-]+$", message = "Fullname must contain only Cyrillic characters and hyphens")
    private String fullname;

    @Column(name = "text")
    @NotEmpty(message = "Comment cannot be empty")
    private String text;

    public Comment(String fullname, String text) {
        this.fullname = fullname;
        this.text = text;
    }
}
