package com.example.mediaclone.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name = "email", columnNames = {}))
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String first_name;
    @Column(nullable = false)
    private String last_name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String date_of_birth;
    @Column(nullable = false)
    private String gender;

    public String formatString(String name) {
        String raw = name;
        String[] rawArr = raw.split("");
        String finAns = "";

        for (int i = 0; i < 1; i++) {
            StringBuilder sb = new StringBuilder(rawArr[i]);
            rawArr[i] = String.valueOf(sb).toUpperCase();
        }

        for(String el : rawArr) {
            finAns+=el;
        }
        return finAns.trim();
    }
}
