package com.example.dotoring.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Menti {
    @Id
    @Column(name = "note_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> noteMentoes = new ArrayList<>();

    @Builder
    public Menti(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void addNoteMentoes(Menti menti){
        this.noteMentoes.add(menti.getUsername());
    }

    public void changeUsername(String username){
        this.username = username;
    }

    public void changePassword(String password){
        this.password = password;
    }
}


