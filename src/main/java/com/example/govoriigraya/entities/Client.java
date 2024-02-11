package com.example.govoriigraya.entities;

import com.example.govoriigraya.excel.ExcelCol;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

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
    @Pattern(regexp = "^[А-Яа-яЁ\\s\\-]+$", message = "Fullname must contain only Cyrillic characters and hyphens")
    @ExcelCol
    private String fullname;

    @Column(name = "phone")
    @Pattern(regexp = "^(\\+7|7|8)?[489][0-9]{9}$",
            message = "Invalid phone number format")
    @ExcelCol
    private String phone;

    @Column(name = "mail")
    @Email(message = "Invalid email format")
    @ExcelCol
    private String mail;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    public Client(String fullname, String phoneNumber, String mail) {
        this.fullname = fullname;
        this.phone = phoneNumber;
        this.mail = mail;
        this.date = Timestamp.from(Instant.now());
    }
}
