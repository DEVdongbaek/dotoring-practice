package com.example.dotoring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Mento {

    @Id
    @Column(name = "note_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> noteMenties = new ArrayList<>();

    @Builder
    public Mento(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void addNoteMenties(Menti menti){
        this.noteMenties.add(menti.getUsername());
    }

    public void changeUsername(String username){
        this.username = username;
    }

    public void changePassword(String password){
        this.password = password;
    }
}
