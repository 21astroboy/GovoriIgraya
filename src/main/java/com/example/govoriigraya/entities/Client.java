package com.example.govoriigraya.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="clients")
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullname")
    @NotEmpty(message = "Fullname cannot be empty")
    @Pattern(regexp = "^[А-Яа-яЁ\\-]+$", message = "Fullname must contain only Cyrillic characters and hyphens")
    private String fullname;

    @Column(name = "phone")
    @Pattern(regexp = "^(\\+7|7|8)?[\\s\\-]?\\(?[489][0-9]{2}\\)?[\\s\\-]?[0-9]{3}[\\s\\-]?[0-9]{2}[\\s\\-]?[0-9]{2}$",
            message = "Invalid phone number format")
    private String phone;
    @Column(name = "mail")
    @Email(message = "Invalid email format")
    private String mail;

    public Client(String fullname, String phoneNumber, String mail) {
        this.fullname = fullname;
        this.phone = phoneNumber;
        this.mail = mail;
    }
}
