package com.example.govoriigraya.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name ="actors")
@Getter
@Setter
@NoArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone")
    @Pattern(regexp = "^(\\+7|7|8)?[489][0-9]{9}$",
            message = "Invalid phone number format")
    private String phone;

    @Column(name = "password")
    @NotEmpty(message = "password cannot be empty")
    private String password;

    @Column(name="firstname")
    @NotEmpty(message = "firstname cannot be empty")
    private String firstname;

    @Column(name = "token")
    private String token;

    @Column(name = "role")
    private String role;

    public Actor(String phone, String name, String password, String role) {
        this.phone = phone;
        this.firstname = name;
        this.password = password;
        this.role = role;
        this.token = generateToken();
    }

    private static String generateToken() {
        int n = 40;
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int rand = ThreadLocalRandom.current().nextInt();
            if (rand < 0) rand = -rand;
            int index = rand % AlphaNumericString.length();
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
